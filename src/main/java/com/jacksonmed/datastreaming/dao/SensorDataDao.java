package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.SensorImage;
import com.jacksonmed.datastreaming.model.Test;

public interface SensorDataDao {
    void insertSensorImageData(SensorImage sensorImage);
    SensorImage retrieveSensorImageData(String uniqueId);

}
