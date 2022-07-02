package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.SensorDataDaoImpl;
import com.jacksonmed.datastreaming.model.SensorImage;
import com.jacksonmed.datastreaming.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SensorImageDataService")
public class SensorDataService {
    @Autowired
    SensorDataDaoImpl sensorImageDao;

    public SensorImage getSensorImageData(String uniqueId) {
        return sensorImageDao.retrieveSensorImageData(uniqueId);
    }

    public void insertSensorImageData(SensorImage sensorImage){
        sensorImageDao.insertSensorImageData(sensorImage);
    }
}







