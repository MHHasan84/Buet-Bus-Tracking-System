package com.example.myapplication2; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestLocalhostBus {

    @SerializedName("busid")
    @Expose
    private Integer busid;
    @SerializedName("busname")
    @Expose
    private String busname;
    @SerializedName("routename")
    @Expose
    private String routename;

    public Integer getBusid() {
        return busid;
    }

    public void setBusid(Integer busid) {
        this.busid = busid;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getRoutename() {
        return routename;
    }

    public void setRoutename(String routename) {
        this.routename = routename;
    }

}
