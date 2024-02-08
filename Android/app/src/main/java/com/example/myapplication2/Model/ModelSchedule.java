package com.example.myapplication2.Model;

public class ModelSchedule {
    private int scheduleId;
    private int busId;
    private int pickupPointId;
    private String time;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getPickupPointId() {
        return pickupPointId;
    }

    public void setPickupPointId(int pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
