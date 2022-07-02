package com.jacksonmed.datastreaming.service;

import com.jacksonmed.datastreaming.dao.PatientInfoDao;
import com.jacksonmed.datastreaming.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PatientService")
public class PatientService {
    @Autowired
    PatientInfoDao patientInfoDao;

    public Patient retrievePatient(String uniqueId) {
        return patientInfoDao.retrievePatient(uniqueId);
    }

    public void insertPatient(Patient patient){
        patientInfoDao.insertPatient(patient);
    }
}
