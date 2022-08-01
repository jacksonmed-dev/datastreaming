package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.Patient;
import com.example.jacksonmed.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

@Component("PatientDaoImpl")
public class PatientDaoImpl implements PatientDao{
    private static final String INSERT_PATIENT_SQL = "INSERT INTO patient" + "  (patient_id, date_of_birth, gender, height, name, preconditions, weight, braden_scale ) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PATIENT_BY_ID = "select patient_id, date_of_birth, gender, height, name, preconditions, weight, braden_scale from patient where patient_id =?";
    private static final String USER = "jacksonmed";
    private static final String PASS = "jacksonmedjms";



    @Override
    public void insertPatient(Patient patient) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PATIENT_SQL);
        preparedStatement.setString(1, patient.getPatientId());
        preparedStatement.setDate(2, java.sql.Date.valueOf( patient.getDateOfBirth()));
        preparedStatement.setString(3, patient.getGender());
        preparedStatement.setInt(4, patient.getHeight());
        preparedStatement.setString(5, patient.getName());
        preparedStatement.setString(6, patient.getPreConditions());
        preparedStatement.setInt(7, patient.getWeight());
        preparedStatement.setInt(8, patient.getBradenScale());

        preparedStatement.executeUpdate();




    }
    @Override
    public Patient retrievePatient(String uniqueId) throws SQLException {
        Patient patient = null;
        // Step 1: Establishing a Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_BY_ID);
        preparedStatement.setString(1, uniqueId);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
            String gender = rs.getString("gender");
            int height = rs.getInt("height");
            String name = rs.getString("name");
            String preconditions = rs.getString("preconditions");
            int weight = rs.getInt("weight");
            int bradenScale = rs.getInt("braden_scale");

            patient = new Patient(uniqueId, name, height, weight,dateOfBirth, gender,  preconditions, bradenScale);
        }

        return patient;
    }


}
