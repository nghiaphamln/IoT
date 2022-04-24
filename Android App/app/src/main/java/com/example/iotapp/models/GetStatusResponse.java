package com.example.iotapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStatusResponse {
    @SerializedName("doorStatus")
    @Expose
    private Integer doorStatus;
    @SerializedName("fanStatus")
    @Expose
    private Boolean fanStatus;
    @SerializedName("lightStatus")
    @Expose
    private Boolean lightStatus;

    public Integer getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(Integer doorStatus) {
        this.doorStatus = doorStatus;
    }

    public Boolean getFanStatus() {
        return fanStatus;
    }

    public void setFanStatus(Boolean fanStatus) {
        this.fanStatus = fanStatus;
    }

    public Boolean getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(Boolean lightStatus) {
        this.lightStatus = lightStatus;
    }
}
