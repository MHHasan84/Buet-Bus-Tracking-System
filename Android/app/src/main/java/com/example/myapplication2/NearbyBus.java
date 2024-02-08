package com.example.myapplication2;

public class NearbyBus {
    private int busId;
    private String arrivalTime;
    private String currentLocation;

    public NearbyBus(int busId, String arrivalTime, String currentLocation) {
        this.busId = busId;
        this.arrivalTime = arrivalTime;
        this.currentLocation = currentLocation;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }
}
