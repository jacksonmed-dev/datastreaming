package com.jacksonmed.datastreaming.dao;

import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.model.SensorImage;

public interface PatientInfoDao {
    void insertPatient(Patient patient);
    Patient retrievePatient(String uniqueId);
}
