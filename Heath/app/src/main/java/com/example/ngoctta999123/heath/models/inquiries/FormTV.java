package com.example.ngoctta999123.heath.models.inquiries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FormTV {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("albumId")
    @Expose
    private String albumId;

    @SerializedName("type")
    @Expose
    private int type;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("date")
    @Expose
    private ArrayList<Integer> date ;

    public FormTV(int id, String album_id, int type, String content, ArrayList<Integer> date) {
        this.id = id;
        this.albumId = album_id;
        this.type = type;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Integer> getDate() {
        return date;
    }

    public void setDate(ArrayList<Integer> date) {
        this.date = date;
    }
}
