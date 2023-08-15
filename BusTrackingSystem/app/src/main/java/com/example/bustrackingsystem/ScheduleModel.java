package com.example.bustrackingsystem;

public class ScheduleModel {
    private String busName;
    private String pickupPoint;
    private String pickupTime;

    public ScheduleModel(String busName, String pickupPoint, String time) {
        this.busName = busName;
        this.pickupPoint = pickupPoint;
        this.pickupTime = time;
    }

    public String getBusName() {
        return busName;
    }

    public String getPickupPoint() {
        return pickupPoint;
    }

    public String getPickupTime() {
        return pickupTime;
    }
}
