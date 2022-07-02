package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.jacksonmed.datastreaming.model.SensorImage;
import com.jacksonmed.datastreaming.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SensorImageDaoImpl")
public class SensorDataDaoImpl implements SensorDataDao {
    private static final String INSERT_SENSOR_IMAGE = "insert into test (name) values (?)";
    private static final String SELECT_SENSOR_IMAGE_BY_UNIQUE_ID = "select * from test where name = ?";
    @Autowired
    CqlSession cqlSession;


    @Override
    public void insertSensorImageData(SensorImage sensorImage) {
        PreparedStatement ps = cqlSession.prepare(INSERT_SENSOR_IMAGE);
        BoundStatement bs = ps.bind()
                .setString(0, String.valueOf(sensorImage))
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        cqlSession.execute(bs);
    }

    @Override
    public SensorImage retrieveSensorImageData(String uniqueId) {
        SensorImage tempSensorImage = new SensorImage();

        PreparedStatement ps = cqlSession.prepare(SELECT_SENSOR_IMAGE_BY_UNIQUE_ID);
        BoundStatement bs = ps.bind()
                .setString(0, uniqueId)
                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
        tempSensorImage = (SensorImage) cqlSession.execute(bs);
        return tempSensorImage;
    }
}
