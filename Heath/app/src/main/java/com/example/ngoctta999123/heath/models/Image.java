package com.example.ngoctta999123.heath.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Image implements Serializable {
    @SerializedName("albumId")
    @Expose
    private String albumId;

    @SerializedName("images")
    @Expose
    private List<String> images;

    public Image(String albumId, List<String> images) {
        this.albumId = albumId;
        this.images = images;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
