package com.crutchesbicycles.gasmen;

import com.crutchesbicycles.gasmen.driver.MQTTDriver;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GasmenApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasmenApplication.class, args);
    }

}
