package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.SensorDataDao;
import com.jacksonmed.datastreaming.dao.SensorDataDaoImpl;
import com.jacksonmed.datastreaming.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SensorImageDataService")
public class SensorDataService {
    @Autowired
    SensorDataDao sensorDataDao;

    public SensorData getSensorData(String uniqueId) {
        return sensorDataDao.retrieveSensorData(uniqueId);
    }

    public void insertSensorData(SensorData sensorData){
        sensorDataDao.insertSensorData(sensorData);
    }
}







