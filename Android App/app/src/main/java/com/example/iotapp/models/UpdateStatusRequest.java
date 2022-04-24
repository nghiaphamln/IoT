package com.example.iotapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateStatusRequest {
    @SerializedName("lightStatus")
    @Expose
    private Boolean lightStatus;
    @SerializedName("fanStatus")
    @Expose
    private Boolean fanStatus;
    @SerializedName("doorStatus")
    @Expose
    private Integer doorStatus;

    public Boolean getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(Boolean lightStatus) {
        this.lightStatus = lightStatus;
    }

    public Boolean getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(Boolean fanStatus) {
        this.fanStatus = fanStatus;
    }

    public Integer getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(Integer doorStatus) {
        this.doorStatus = doorStatus;
    }
}
