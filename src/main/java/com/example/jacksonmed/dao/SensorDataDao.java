package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.SensorData;

import java.io.IOException;
import java.sql.SQLException;

public interface SensorDataDao {
    void insertSensorData(SensorData sensorData) throws SQLException, IOException;
    SensorData retrieveSensorData(String uniqueId) throws SQLException;



}