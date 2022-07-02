package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.Patient;

public interface PatientDao {
    void insertPatient(Patient patient);
    Patient retrievePatient(String uniqueId);
}
