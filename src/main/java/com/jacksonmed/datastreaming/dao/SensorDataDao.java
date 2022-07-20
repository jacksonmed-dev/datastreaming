package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.SensorData;

public interface SensorDataDao {
    void insertSensorData(SensorData sensorData);
    SensorData retrieveSensorData(String uniqueId);



}
