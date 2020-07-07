package com.example.ngoctta999123.heath.models.records;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;   //tên bệnh nhân

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("practitioner")
    @Expose
    private Practitioner practitioner;   //thông tin bác sĩ

    public Patient(int id, String name, int age, Practitioner practitioner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.practitioner = practitioner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Practitioner getPractitioner() {
        return practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
