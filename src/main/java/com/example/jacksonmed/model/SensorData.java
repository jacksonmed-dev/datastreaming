package com.example.jacksonmed.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Table(name = "sensor_data")
@IdClass(SensorDataPk.class)
public class SensorData {
    @Id
    @Column(name = "patient_id")
    public String patientId;

    @Id
    @Column(name = "time_stamp")
    public String timeStamp;

    @Column(name = "sensor_data_id")
    public String sensorDataId;

    @Column(name = "sensor_image")
    public String sensorImage;

    public SensorData(String patientId, String sensorDataId, String timeStamp, String sensorImage) {
        this.patientId = patientId;
        this.sensorDataId = sensorDataId;
        this.timeStamp = timeStamp;
        this.sensorImage = sensorImage;
    }

    public SensorData() {}


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(String sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSensorImage() {
        return sensorImage;
    }

    public void setSensorImage(String sensorImage) {
        this.sensorImage = sensorImage;
    }


}