package com.jacksonmed.datastreaming.model;

import javax.persistence.*;
import java.time.LocalDate;


@Table(name = "sensor_data")
//@IdClass(SensorDataPk.class)
public class SensorData {
    @Id
    @Column(name = "patient_id")
    public String patientId;

    @Id
    @Column(name = "time_stamp")
    public LocalDate timeStamp;

    @Column(name = "sensor_data_id")
    public String sensorDataId;

    @Column(name = "sensor_image")
    public byte[] sensorImage;

    public SensorData(String patientId, String sensorDataId, LocalDate timeStamp, byte[] sensorImage) {
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

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public byte[] getSensorImage() {
        return sensorImage;
    }

    public void setSensorImage(byte[] sensorImage) {
        this.sensorImage = sensorImage;
    }
}
