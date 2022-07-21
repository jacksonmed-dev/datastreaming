package com.jacksonmed.datastreaming.dao;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.jacksonmed.datastreaming.model.Patient;
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
import java.util.Iterator;
@Component("SensorDataDaoImpl")
public class SensorDataDaoImpl implements SensorDataDao {
    private static final String INSERT_SENSOR_DATA = "insert into sensor_data (patient_id, time_stamp, sensor_data_id, sensor_image) values (?, ?, ?, ?)";
    private static final String SELECT_SENSOR_DATA_BY_UNIQUE_ID = "select * from sensor_data where patient_id = ?";
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


    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
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
            SensorData sensorData = null;
            PreparedStatement ps = cqlSession.prepare(SELECT_SENSOR_DATA_BY_UNIQUE_ID);
            BoundStatement bs = ps.bind()
                    .setString(0, uniqueId)
                    .setConsistencyLevel(ConsistencyLevel.LOCAL_QUORUM);
            ResultSet rs = cqlSession.execute(bs);
            Iterator<Row> rsIterator = rs.iterator();
            Blob image;
            if (rsIterator.hasNext())
            {
                Row row = rsIterator.next();
                sensorData = new SensorData();
                sensorData.setPatientId(row.getString("patient_id"));
                sensorData.setTimeStamp(String.valueOf(row.getInstant("time_stamp")));
                sensorData.setSensorDataId(row.getString("sensor_data_id"));
                // bytebuffer to byte array to Hex string
                sensorData.setSensorImage(bytesToHex((row.getByteBuffer("sensor_image")).array()));


            }
            return sensorData;
        }
}
