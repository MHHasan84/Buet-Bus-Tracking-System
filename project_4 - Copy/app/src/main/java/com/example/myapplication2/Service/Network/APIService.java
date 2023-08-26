package com.example.myapplication2.Service.Network;

import com.example.myapplication2.Service.Model.BusModel;
import com.example.myapplication2.Service.Model.DriverModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    //Bus
    @GET("bus")
    Call<ArrayList<BusModel>> getAllBus();
    @GET("bus/{id}")
    Call<BusModel> getBus(@Path("id") int id);
    @POST("bus")
    Call<BusModel> addBus(@Body BusModel busModel);
    @POST("bus/{id}")
    Call<BusModel> editBus(@Body BusModel busModel,@Path("id") int id);

    //Person
    @GET("driver")
    Call<List<DriverModel>> getAllDriver();
}
