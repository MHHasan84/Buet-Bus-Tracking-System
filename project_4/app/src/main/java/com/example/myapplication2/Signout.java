package com.example.myapplication2; ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signout {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}