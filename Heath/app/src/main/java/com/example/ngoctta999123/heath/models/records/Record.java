package com.example.ngoctta999123.heath.models.records;

import com.example.ngoctta999123.heath.models.Doctor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("doctor")
    @Expose
    private Doctor doctor;

    @SerializedName("inquiry")
    @Expose
    private Inquiry inquiry;

    @SerializedName("diagnose")
    @Expose
    private String diagnose;   //chẩn đoán của bsi

    @SerializedName("note")
    @Expose
    private String note;   // tư vấn của bsi

    @SerializedName("disease")
    @Expose
    private String disease;   // tên bệnh

    @SerializedName("prescription")
    @Expose
    private String prescription;   // đơn thuốc

    @SerializedName("recordType")
    @Expose
    private int recordType;   // 0. khám bệnh   1. dinh dưỡng

    public Record(int id, Inquiry inquiry, Doctor doctor, String diagnose, String note, String disease, String prescription, int recordType) {
        this.id = id;
        this.inquiry = inquiry;
        this.doctor = doctor;
        this.diagnose = diagnose;
        this.note = note;
        this.disease = disease;
        this.prescription = prescription;
        this.recordType = recordType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inquiry getInquiry() {
        return inquiry;
    }

    public void setInquiry(Inquiry inquiry) {
        this.inquiry = inquiry;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
