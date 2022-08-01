package com.example.jacksonmed.dao;

import com.example.jacksonmed.model.Patient;
import com.example.jacksonmed.model.SensorData;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@Component("SensorDataDaoImpl")
public class SensorDataDaoImpl implements SensorDataDao {

    private static final String INSERT_SENSOR_DATA_SQL = "INSERT INTO sensor_data" + "  (patient_id, time_stamp, sensor_data_id, sensor_image ) VALUES "
            + " (?, ?, ?, ?);";
    private static final String SELECT_SENSOR_DATA_BY_ID = "select * from sensor_data where patient_id =? " ;
    private static final String USER = "jacksonmed";
    private static final String PASS = "jacksonmedjms";

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
    public void insertSensorData(SensorData sensorData) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SENSOR_DATA_SQL);
        preparedStatement.setString(1, sensorData.getPatientId());
        preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(sensorData.getTimeStamp()));
        preparedStatement.setString(3, sensorData.getSensorDataId());
        preparedStatement.setBytes(4, convertFileContentToBlob(sensorData.getSensorImage()));

        preparedStatement.executeUpdate();


    }

    @Override
    public SensorData retrieveSensorData(String uniqueId) throws SQLException {
        SensorData sensorData = null;
        // Step 1: Establishing a Connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://jacksonmeddb.c3hoyglkaipl.us-west-1.rds.amazonaws.com:3306/jackson_med", USER, PASS);
        // Step 2:Create a statement using connection object
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SENSOR_DATA_BY_ID);
        preparedStatement.setString(1, uniqueId);


        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            String time_stamp = String.valueOf(rs.getTimestamp("time_stamp"));
            String sensor_data_id = rs.getString("sensor_data_id");
            String sensor_image = bytesToHex(rs.getBytes("sensor_image"));


            sensorData = new SensorData(uniqueId, time_stamp, sensor_data_id, sensor_image);
        }

        return sensorData;
    }
}
