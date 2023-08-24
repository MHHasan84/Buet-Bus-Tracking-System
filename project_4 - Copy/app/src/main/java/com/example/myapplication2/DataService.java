package com.example.myapplication2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataService {
    @POST("auth/signup")
    Call<User> signUp(@Body User user);

    @POST("auth/signins")
    Call<User> signIn(@Body User user);

    @GET("trackGPS/get-all")
    Call<List<Track>> getAllTrack();


    @GET("trackGPS/get-by-id/{id}")
    Call<Track> getTrack(@Path("id") int id);

    @GET("bus/get/{id}")
    Call<TestBus> getTestBus(@Path("id") int id);

    @POST("auth/signout")
    Call<Signout> signOut();
}
