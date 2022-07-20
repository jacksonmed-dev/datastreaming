package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.jacksonmed.datastreaming.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component("SensorDataDaoImpl")
public class SensorDataDaoImpl implements SensorDataDao {
    private static final String INSERT_SENSOR_DATA = "insert into sensor_data (patient_id, time_stamp, sensor_data_id, sensor_image) values (?, ?, ?, ?)";
    private static final String SELECT_SENSOR_DATA_BY_UNIQUE_ID = "select * from test where name = ?";
    @Autowired
    CqlSession cqlSession;

    public SensorDataDaoImpl() throws IOException, SQLException {
    }


    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
        // create file object
        File file = new File(filePath);
        // initialize a byte array of size of the file
        byte[] fileContent = new byte[(int) file.length()];
        FileInputStream inputStream = null;
        try {
            // create an input stream pointing to the file
            inputStream = new FileInputStream(file);
            // read the contents of file into byte array
            inputStream.read(fileContent);
        } catch (IOException e) {
            throw new IOException("Unable to convert file to byte array. " +
                    e.getMessage());
        } finally {
            // close input stream
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return fileContent;
    }





    @Override
    public void insertSensorData(SensorData sensorData){
        try {
            PreparedStatement ps = cqlSession.prepare(INSERT_SENSOR_DATA);



            BoundStatement bs = ps.bind()
                    .setString(0, sensorData.getPatientId())
                    .setInstant(1, LocalDate.parse(sensorData.getTimeStamp()).atStartOfDay(ZoneId.of("America/Phoenix")).toInstant())
                    .setString(2, sensorData.getSensorDataId())
                    .setBytesUnsafe(3, ByteBuffer.wrap(convertFileContentToBlob(sensorData.getSensorImage())))
                    .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
            cqlSession.execute(bs);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SensorData retrieveSensorData(String uniqueId) {
        return null;
    }


//    @Override
//    public SensorData retrieveSensorData(String uniqueId) {
//        Patient patient = null;
//        PreparedStatement ps = cqlSession.prepare(SELECT_PATIENT_INFO_BY_UNIQUE_ID);
//        BoundStatement bs = ps.bind()
//                .setString(0, uniqueId)
//                .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
//        ResultSet rs = cqlSession.execute(bs);
//        Iterator<Row> rsIterator = rs.iterator();
//        if (rsIterator.hasNext())
//        {
//            Row row = rsIterator.next();
//            patient = new Patient();
//            patient.setPatientId(row.getString("patient_id"));
//            patient.setBradenScale(row.getInt("braden_scale"));
//            patient.setDateOfBirth(row.getLocalDate("date_of_birth"));
//            patient.setGender(row.getString("gender"));
//            patient.setHeight(row.getInt("height"));
//            patient.setName(row.getString("name"));
//            patient.setPreConditions(row.getString("preconditions"));
//            patient.setWeight(row.getInt("weight"));
//
//        }
//        return patient;
//    }
//    }
}
