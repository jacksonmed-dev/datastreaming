package com.jacksonmed.datastreaming.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@IdClass(SensorDataPk.class)
public class SensorData {
    @Id
    public String patientId;
    public String sensorDataId;
    @Id
    public LocalDate timeStamp;
    public byte[] sensorImage;

    public SensorData(String patientId, String sensorDataId, LocalDate timeStamp, byte[] sensorImage) {
        this.patientId = patientId;
        this.sensorDataId = sensorDataId;
        this.timeStamp = timeStamp;
        this.sensorImage = sensorImage;
    }

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
