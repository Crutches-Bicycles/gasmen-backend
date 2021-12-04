package com.crutchesbicycles.gasmen.service;


import com.crutchesbicycles.gasmen.driver.MQTTDriver;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.Mqtt5RxClient;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;
import com.hivemq.client.mqtt.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck;
import com.hivemq.client.rx.FlowableWithSingle;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqttService {
    Mqtt5Client cl;

    public void getMessage() {
        log.warn("START");
        Mqtt5RxClient client = cl.toRx();
        Single<Mqtt5ConnAck> conn = client.toRx().connect();
        Single<Mqtt5ConnAck> connAckSingle = client.connect();

        FlowableWithSingle<Mqtt5Publish, Mqtt5SubAck> subAckAndMatchingPublishes = client.subscribeStreamWith()
                .topicFilter("/trpo/gasmen").qos(MqttQos.AT_LEAST_ONCE)
                .applySubscribe();


        Completable connectScenario = connAckSingle
                .doOnSuccess(connAck -> log.warn("Connected, " + connAck.getReasonCode()))
                .doOnError(throwable -> log.warn("Connection failed, " + throwable.getMessage()))
                .ignoreElement();

        Completable subscribeScenario = subAckAndMatchingPublishes
                .doOnSingle(subAck -> log.warn("Subscribed, " + subAck.getReasonCodes()))
                .doOnNext(publish -> log.warn(
                        "Received publish" + ", topic: " + publish.getTopic() + ", QoS: " + publish.getQos() +
                                ", payload: " + new String(publish.getPayloadAsBytes())))
                .ignoreElements();

        connectScenario.andThen(subscribeScenario).blockingAwait();
    }

    @Autowired
    public MqttService() {
        this.cl = MQTTDriver.getInstance().getClient();
    }
}
