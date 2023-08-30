package com.example.myapplication2;

public class BusViewModel {
    private String route;
    private int imgId;

    public BusViewModel(String route, int imgId) {
        this.route = route;
        this.imgId = imgId;
    }

    public String getRoute() {
        return route;
    }

    public int getImgId() {
        return imgId;
    }
}
