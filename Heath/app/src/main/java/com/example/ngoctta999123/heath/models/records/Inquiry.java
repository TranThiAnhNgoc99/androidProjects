package com.example.ngoctta999123.heath.models.records;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Inquiry {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("patient")
    @Expose
    private Patient patient;   //thong tin benh nhan va bac si

    @SerializedName("content")
    @Expose
    private String content;   //chẩn đoán của bác sĩ

    @SerializedName("type")
    @Expose
    private int type;   //loại : 0. khám   1. dinh dưỡng

    @SerializedName("updatedAt")
    @Expose
    private List<Integer> updatedAt;

    public Inquiry(int id, Patient patient, String content, int type, List<Integer> updatedAt) {
        this.id = id;
        this.patient = patient;
        this.content = content;
        this.type = type;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(List<Integer> updatedAt) {
        this.updatedAt = updatedAt;
    }
}
