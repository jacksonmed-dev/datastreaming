package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.jacksonmed.datastreaming.model.Patient;
import com.jacksonmed.datastreaming.model.SensorImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("PatientInfoDaoImpl")
public class PatientInfoDaoImpl implements PatientInfoDao {
    private static final String INSERT_PATIENT_INFO = "insert into test (name) values (?)";
    private static final String SELECT_PATIENT_INFO_BY_UNIQUE_ID = "select * from test where name = ?";
    @Autowired
    CqlSession cqlSession;


    @Override
    public void insertPatient(Patient patient) {
        PreparedStatement ps = cqlSession.prepare(INSERT_PATIENT_INFO);
        BoundStatement bs = ps.bind()
                .setString(0, String.valueOf(patient))
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        cqlSession.execute(bs);
    }

    @Override
    public Patient retrievePatient(String uniqueId) {
        Patient tempPatient;

        PreparedStatement ps = cqlSession.prepare(SELECT_PATIENT_INFO_BY_UNIQUE_ID);
        BoundStatement bs = ps.bind()
                .setString(0, uniqueId)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        tempPatient = (Patient) cqlSession.execute(bs);
        return tempPatient;
    }
}
