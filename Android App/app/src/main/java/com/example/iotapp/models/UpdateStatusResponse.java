package com.example.iotapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateStatusResponse {
    @SerializedName("isError")
    @Expose
    private Boolean isError;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getIsError() {
        return isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
