package com.crutchesbicycles.gasmen.controller;

import com.crutchesbicycles.gasmen.domain.Sensor;
import com.crutchesbicycles.gasmen.service.MqttService;
import com.crutchesbicycles.gasmen.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final MqttService mqttService;
    private final SensorService sensorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sensor> getSensorsInfo(){
        mqttService.getMessage();
        return sensorService.getSensorInfo(ThreadLocalRandom.current().nextInt(1, 20));
    }

    @Autowired
    public SensorController(SensorService sensorService, MqttService mqttService) {
        this.sensorService = sensorService;
        this.mqttService = mqttService;
    }
}
