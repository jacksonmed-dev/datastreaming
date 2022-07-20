package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.model.SensorData;
import com.jacksonmed.datastreaming.service.PatientService;
import com.jacksonmed.datastreaming.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorDataController {
    @Autowired
    SensorDataService sensorDataService;
    @GetMapping("/sensor_data/{sensorDataId}")
    public SensorData getSensorData(@PathVariable String sensorDataId) {
        return sensorDataService.getSensorData(sensorDataId);
    }
    @PostMapping("/sensor_data/insert")
    @ResponseBody
    public void insert(@RequestBody SensorData sensorData) {
        sensorDataService.insertSensorData(sensorData);
    }

}
