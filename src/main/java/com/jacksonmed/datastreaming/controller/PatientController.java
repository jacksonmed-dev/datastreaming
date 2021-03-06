package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("PatientController")
public class PatientController {
    @Autowired
    PatientService patientService;

//    @RequestMapping(value = "/patient", method = RequestMethod.GET, produces = "application/json")

    @PostMapping("/sensor_data")
    public void insert(@RequestBody Patient patient) {
        patientService.insertPatient(patient);
    }

    @GetMapping("/sensor_data")
    public Patient get(String uniqueId) {
        return patientService.retrievePatient(uniqueId);
    }
}
