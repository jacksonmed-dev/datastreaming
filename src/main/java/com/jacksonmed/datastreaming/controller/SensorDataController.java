package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.SensorImage;
import com.jacksonmed.datastreaming.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("SensorDataController")
public class SensorDataController {
    @Autowired
    SensorDataService sensorDataService;

    @PutMapping("/sensor_data")
    public String insert() {
        SensorImage test = new SensorImage();
        sensorDataService.insertSensorImageData(test);
        return null;
    }

    @GetMapping("/sensor_data")
    public SensorImage get() {
        String tempId = "TEST";
        return sensorDataService.getSensorImageData(tempId);
    }


}
