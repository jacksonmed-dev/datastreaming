package com.example.jacksonmed.controller;

import com.example.jacksonmed.model.SensorData;
import com.example.jacksonmed.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@RestController
public class SensorDataController {
    @Autowired
    SensorDataService sensorDataService;

    @GetMapping("/sensor_data/{sensorDataId}")
    public SensorData getSensorData(@PathVariable String sensorDataId) throws SQLException {
        return sensorDataService.getSensorData(sensorDataId);
    }

    @PostMapping("/sensor_data/insert")
    @ResponseBody
    public void insert(@RequestBody SensorData sensorData) throws SQLException, IOException {

        sensorDataService.insertSensorData(sensorData);
    }
}
