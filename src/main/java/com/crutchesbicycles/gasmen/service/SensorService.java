package com.crutchesbicycles.gasmen.service;

import com.crutchesbicycles.gasmen.domain.Sensor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SensorService {
    public List<Sensor> getSensorInfo(int size){
        List<Sensor> sensors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            sensors.add(getRandomObject());
        }

        return sensors;
    }

    private synchronized Sensor getRandomObject(){
        List<String> locations = Arrays.asList("Office", "Home", "Work", "Dacha");
        List<String> models = Arrays.asList("AHGJH", "JFDGH", "DHGHJ", "EYEUO", "QTKGN");
        ThreadLocalRandom r = ThreadLocalRandom.current();
        Sensor sensor = new Sensor();

        sensor.setId(r.nextLong(0,1000));
        sensor.setLocation(locations.get(r.nextInt(locations.size())));
        sensor.setModel(models.get(r.nextInt(models.size())));
        sensor.setWarranty(LocalDateTime.now().minusMinutes(r.nextInt(40)).minusSeconds(r.nextInt(60)));
        sensor.setIdSensorStatus(r.nextInt(5));
        sensor.setIdSensorType(r.nextInt(4));
        sensor.setValue(r.nextDouble(0.12, 100.23));
        sensor.setDate(LocalDateTime.now().minusSeconds(r.nextInt(400)));

        return sensor;
    }
}
