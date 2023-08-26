package com.example.myapplication2.Service.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusModel {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("busNo")
    @Expose
    private String busNo;
    @SerializedName("busType")
    @Expose
    private String busType;
    @SerializedName("driverId")
    @Expose
    private int driverId;
    @SerializedName("helperId")
    @Expose
    private int helperId;
    @SerializedName("route")
    @Expose
    private String route;
}
