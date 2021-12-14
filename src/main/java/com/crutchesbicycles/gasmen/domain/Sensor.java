package com.crutchesbicycles.gasmen.domain;

import java.time.LocalDateTime;

public class Sensor {
    public Long id;
    public String location;
    public String model;
    public LocalDateTime warranty;
    public String idSensorStatus;
    public Integer idSensorType;
    public Double value;
    public LocalDateTime date;

    public Sensor() {
    }

    public Sensor(Long id, String location, String model, LocalDateTime warranty, String idSensorStatus,
                  Integer idSensorType, Double value, LocalDateTime date) {
        this.id = id;
        this.location = location;
        this.model = model;
        this.warranty = warranty;
        this.idSensorStatus = idSensorStatus;
        this.idSensorType = idSensorType;
        this.value = value;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDateTime getWarranty() {
        return warranty;
    }

    public void setWarranty(LocalDateTime warranty) {
        this.warranty = warranty;
    }

    public String getIdSensorStatus() {
        return idSensorStatus;
    }

    public void setIdSensorStatus(String idSensorStatus) {
        this.idSensorStatus = idSensorStatus;
    }

    public Integer getIdSensorType() {
        return idSensorType;
    }

    public void setIdSensorType(Integer idSensorType) {
        this.idSensorType = idSensorType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
