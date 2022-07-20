package com.jacksonmed.datastreaming.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class BradenScale {
    @PrimaryKey
    public int patientId;
    public String name;
    public int sensoryPerception;
    public int moisture;
    public int activity;
    public int mobility;
    public int nutrition;
    public int frictionAndShear;


}
