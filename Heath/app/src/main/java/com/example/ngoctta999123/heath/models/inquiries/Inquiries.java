package com.example.ngoctta999123.heath.models.inquiries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Inquiries implements Serializable {
    @SerializedName("content")
    @Expose
    private ArrayList<FormTV> content;

    @SerializedName("totalElements")
    @Expose
    private int totalElements;

    public Inquiries(ArrayList<FormTV> formTVS, int totalElements) {
        this.content = formTVS;
        this.totalElements = totalElements;
    }

    public ArrayList<FormTV> getContent() {
        return content;
    }

    public void setContent(ArrayList<FormTV> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
