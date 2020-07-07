package com.example.ngoctta999123.heath.models.inquiries;

public class CreateInquiriRequest {
    private String album_id;
    private String content;
    private String date;
    private int type;

    public CreateInquiriRequest(String album_id, String content, String date, int type) {
        this.album_id = album_id;
        this.content = content;
        this.date = date;
        this.type = type;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
