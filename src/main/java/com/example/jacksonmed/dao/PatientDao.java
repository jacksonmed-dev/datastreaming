package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.Patient;

import java.sql.SQLException;

public interface PatientDao {
    void insertPatient(Patient patient) throws SQLException;
    Patient retrievePatient(String uniqueId) throws SQLException;
}
