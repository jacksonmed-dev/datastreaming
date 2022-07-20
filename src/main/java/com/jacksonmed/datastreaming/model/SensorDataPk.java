package com.jacksonmed.datastreaming.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@EqualsAndHashCode

public class SensorDataPk implements Serializable {

    public String patientId;
    public String sensorDataId;
    public Timestamp timeStamp;

    public SensorDataPk(String patientId, String sensorDataId, Timestamp timeStamp) {
        this.patientId = patientId;
        this.sensorDataId = sensorDataId;
        this.timeStamp = timeStamp;
    }
}
