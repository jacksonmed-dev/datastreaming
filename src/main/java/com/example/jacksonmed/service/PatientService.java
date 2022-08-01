package com.example.jacksonmed.service;

import com.example.jacksonmed.dao.PatientDao;
import com.example.jacksonmed.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service("PatientService")
public class PatientService {
    @Autowired
    PatientDao patientDao;
    public Patient retrievePatient(String uniqueId) throws SQLException {
        return patientDao.retrievePatient(uniqueId);
    }

    public void insertPatient(Patient patient) throws SQLException {
        patientDao.insertPatient(patient);
    }
}