package com.example.jacksonmed.controller;

import com.example.jacksonmed.model.Patient;
import com.example.jacksonmed.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController("PatientController")
public class PatientController {
    @Autowired
    PatientService patientService;

//    @RequestMapping(value = "/patient", method = RequestMethod.GET, produces = "application/json")

    @PostMapping("/sensor_data/patient")
    @ResponseBody
    public void insert(@RequestBody Patient patient) throws SQLException {
        patientService.insertPatient(patient);
    }

    @GetMapping("/sensor_data/patient/{uniqueId}")
    @ResponseBody
    public Patient get(@PathVariable String uniqueId) throws SQLException {
        return patientService.retrievePatient(uniqueId);
    }
}