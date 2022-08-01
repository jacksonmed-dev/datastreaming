CREATE DATABASE jackson_med;
USE jackson_med;
CREATE table sensor_data (
                      patient_id VARCHAR(255),
                      time_stamp VARCHAR(255),
                      sensor_data_id VARCHAR(255),
                      sensor_image VARCHAR(255),
                      PRIMARY KEY (patient_id, time_stamp)


);
DROP TABLE sensor_data;
desc test;