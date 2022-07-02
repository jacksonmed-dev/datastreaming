package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.PatientDao;
import com.jacksonmed.datastreaming.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientService {
    @Autowired
    PatientDao patientDao;

    public Patient retrievePatient(String uniqueId) {
        return patientDao.retrievePatient(uniqueId);
    }

    public void insertPatient(Patient patient){
        patientDao.insertPatient(patient);
    }
}
