package com.jacksonmed.datastreaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Date;
import java.sql.Time;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(MachineLearningModelPk.class)

public class MachineLearningModel {
    @Id
    private Date date;
    @Id
    private Time hourMinute;
}
