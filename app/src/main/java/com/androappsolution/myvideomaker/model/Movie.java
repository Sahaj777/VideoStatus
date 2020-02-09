package com.androappsolution.myvideomaker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {

    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("FileUrl")
    @Expose
    private String fileUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


}