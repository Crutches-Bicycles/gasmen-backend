package com.crutchesbicycles.gasmen.driver;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
public class MQTTDriver {


    private Mqtt5Client client;
    private static MQTTDriver instance;

    private MQTTDriver(){
        client = MqttClient.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost("broker.hivemq.com")
                .useMqttVersion5()
                .build();
    }

    public static synchronized MQTTDriver getInstance() {
        if (instance == null) {
            instance = new MQTTDriver();
        }

        return instance;
    }

    public Mqtt5Client getClient() {
        return client;
    }

}
