package com.example.myapplication2.Model;

public class ModelBusPerson {
    private int busPersonId;
    private String name;
    private String contactNumber;
    private String busPersonType;
    private String drivingLicenseNo;
    private String nidNo;

    public int getBusPersonId() {
        return busPersonId;
    }

    public void setBusPersonId(int busPersonId) {
        this.busPersonId = busPersonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getBusPersonType() {
        return busPersonType;
    }

    public void setBusPersonType(String busPersonType) {
        this.busPersonType = busPersonType;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public String getNidNo() {
        return nidNo;
    }

    public void setNidNo(String nidNo) {
        this.nidNo = nidNo;
    }
}
