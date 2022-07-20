package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.jacksonmed.datastreaming.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component("PatientInfoDaoImpl")
public class PatientDaoImpl implements PatientDao {
    private static final String INSERT_PATIENT_INFO = "insert into patient (patient_id, braden_scale, date_of_birth, gender, height, name, preconditions, weight) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PATIENT_INFO_BY_UNIQUE_ID = "select * from patient where patient_id = ?";
    @Autowired
    CqlSession cqlSession;


    @Override
    public void insertPatient(Patient patient) {
        PreparedStatement ps = cqlSession.prepare(INSERT_PATIENT_INFO);
        BoundStatement bs = ps.bind()
                .setString(0, patient.getPatientId())
                .setInt(1, patient.getBradenScale())
                .setLocalDate(2,  patient.getDateOfBirth())
                .setString(3, patient.getGender())
                .setInt(4, patient.getHeight())
                .setString(5, patient.getName())
                .setString(6, patient.getPreConditions())
                .setInt(7, patient.getWeight())
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        cqlSession.execute(bs);
    }

    @Override
    public Patient retrievePatient(String uniqueId) {
        Patient patient = null;
        PreparedStatement ps = cqlSession.prepare(SELECT_PATIENT_INFO_BY_UNIQUE_ID);
        BoundStatement bs = ps.bind()
                .setString(0, uniqueId)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        ResultSet rs = cqlSession.execute(bs);
        Iterator<Row> rsIterator = rs.iterator();
        if (rsIterator.hasNext())
        {
            Row row = rsIterator.next();
            patient = new Patient();
            patient.setPatientId(row.getString("patient_id"));
            patient.setBradenScale(row.getInt("braden_scale"));
            patient.setDateOfBirth(row.getLocalDate("date_of_birth"));
            patient.setGender(row.getString("gender"));
            patient.setHeight(row.getInt("height"));
            patient.setName(row.getString("name"));
            patient.setPreConditions(row.getString("preconditions"));
            patient.setWeight(row.getInt("weight"));

        }
        return patient;
    }
}
