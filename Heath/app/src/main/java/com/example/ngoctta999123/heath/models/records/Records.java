package com.example.ngoctta999123.heath.models.records;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class Records implements Serializable {
    @SerializedName("content")
    @Expose
    private ArrayList<Record> content;

    @SerializedName("totalElements")
    @Expose
    private int totalElements;

    public Records(ArrayList<Record> records, int totalElements) {
        this.content = records;
        this.totalElements = totalElements;
    }

    public ArrayList<Record> getContent() {
        return content;
    }

    public void setContent(ArrayList<Record> content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
}
