package com.example.myapplication2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @POST("auth/signup")
    Call<User> signUp(@Body User user);

    @POST("auth/signins")
    Call<User> signIn(@Body User user);

    @GET("trackGPS/get-all")
    Call<List<Track>> getAllTrack();
}
