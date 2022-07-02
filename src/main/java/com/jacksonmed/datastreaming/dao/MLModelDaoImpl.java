package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.jacksonmed.datastreaming.model.MLModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MLModelDaoImpl")
public class MLModelDaoImpl implements MLModelDao {
    private static final String INSERT_PATIENT_INFO = "insert into test (name) values (?)";
    private static final String SELECT_PATIENT_INFO_BY_UNIQUE_ID = "select * from test where name = ?";
    @Autowired
    CqlSession cqlSession;

    @Override
    public void insertMLModel(MLModel mlModel) {
        PreparedStatement ps = cqlSession.prepare(INSERT_PATIENT_INFO);
        BoundStatement bs = ps.bind()
                .setString(0, String.valueOf(mlModel))
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        cqlSession.execute(bs);
    }

    @Override
    public MLModel retrieveMLModel(String uniqueId) {
        MLModel mlModel;

        PreparedStatement ps = cqlSession.prepare(SELECT_PATIENT_INFO_BY_UNIQUE_ID);
        BoundStatement bs = ps.bind()
                .setString(0, uniqueId)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        mlModel = (MLModel) cqlSession.execute(bs);
        return mlModel;
    }
}
