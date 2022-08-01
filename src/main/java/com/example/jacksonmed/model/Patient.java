package com.example.jacksonmed.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Table(name = "patient")
public class Patient {

    @Id
    @Column(name="patient_id")
    public String patientId;

    @Column(name="name")
    public String name;
    @Column(name="height")
    public int height;
    @Column(name="weight")
    public int weight;
    @Column(name="date_of_birth")
    public LocalDate dateOfBirth;
    @Column(name="gender")
    public String gender;
    @Column(name="preconditions")
    public String preConditions;

    @Column(name="braden_scale")
    public int bradenScale;
    public Patient() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public Patient(String patientId, String name, int height, int weight, LocalDate dateOfBirth, String gender, String preConditions, int bradenScale) {
        this.patientId = patientId;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.preConditions = preConditions;
        this.bradenScale = bradenScale;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPreConditions() {
        return preConditions;
    }

    public void setPreConditions(String preConditions) {
        this.preConditions = preConditions;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBradenScale() {
        return bradenScale;
    }

    public void setBradenScale(int bradenScale) {
        this.bradenScale = bradenScale;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return weight == patient.weight && name.equals(patient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}