package com.example.jacksonmed.service;

import com.example.jacksonmed.dao.SensorDataDao;
import com.example.jacksonmed.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service("SensorImageDataService")
public class SensorDataService {
    @Autowired
    SensorDataDao sensorDataDao;

    public SensorData getSensorData(String uniqueId) throws SQLException {
        return sensorDataDao.retrieveSensorData(uniqueId);
    }

    public void insertSensorData(SensorData sensorData) throws SQLException, IOException {
        sensorDataDao.insertSensorData(sensorData);
    }
}