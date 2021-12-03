package com.crutchesbicycles.gasmen.service;


import com.crutchesbicycles.gasmen.driver.MQTTDriver;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;
import io.reactivex.Completable;
import io.reactivex.Single;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MqttService {
    Mqtt5Client client;

    public void getMessage() {
        log.warn("START");
        Single<Mqtt5ConnAck> conn = client.toRx().connect();
        Completable connectScenario = conn
                .doOnSuccess(connAck -> log.warn("Connected, " + connAck.getReasonCode()))
                .doOnError(throwable -> log.warn("Connection failed, " + throwable.getMessage()))
                .ignoreElement();
    }

    @Autowired
    public MqttService() {
        this.client = MQTTDriver.getInstance().getClient();
    }
}
