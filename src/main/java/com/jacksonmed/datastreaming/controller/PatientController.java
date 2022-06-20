package com.jacksonmed.datastreaming.controller;

import com.jacksonmed.datastreaming.model.Patient;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {
    Patient patient = new Patient();

    @RequestMapping(value = "/patient", method = RequestMethod.GET, produces = "application/json")
    public Patient getPatient() {
        return patient;
    }

    @PostMapping
    public Patient create(@RequestBody Patient patient) {
        System.out.println(patient);
        return patient;
    }
}
