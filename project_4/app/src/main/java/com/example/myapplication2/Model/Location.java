package com.example.myapplication2.Model;

public class Location {
    private String locationId;
    private double latitude;
    private double longitude;

    public Location(){

    }

    public Location(String locationId, double latitude, double longitude) {
        this.locationId = locationId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
