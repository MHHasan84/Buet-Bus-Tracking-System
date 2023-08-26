package com.example.myapplication2.Model;

public class ModelPickupPoint {
    private int pickupPointId;
    private int routeId;
    private String pickupPointName;

    public int getPickupPointId() {
        return pickupPointId;
    }

    public void setPickupPointId(int pickupPointId) {
        this.pickupPointId = pickupPointId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getPickupPointName() {
        return pickupPointName;
    }

    public void setPickupPointName(String pickupPointName) {
        this.pickupPointName = pickupPointName;
    }
}
