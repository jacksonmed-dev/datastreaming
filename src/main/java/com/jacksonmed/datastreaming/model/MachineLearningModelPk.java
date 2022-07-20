package com.jacksonmed.datastreaming.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
@Data
@NoArgsConstructor
@EqualsAndHashCode

public class MachineLearningModelPk implements Serializable {

    private Date date;
    private Time hourMinute;

}
