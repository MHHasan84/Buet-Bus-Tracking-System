package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BusService {
    @GET("bus")
    Call<List<ModelBus>> getAllBus();
    @GET("bus/{id}")
    Call<ModelBus> getBus(@Path("id") int busId);
    @POST("bus")
    Call<ModelBus> addBus(@Body ModelBus modelBus);
    @PUT("bus/{id}")
    Call<ModelBus> editBus(@Body ModelBus modelBus,@Path("id") int busId);
    @DELETE("bus/{id}")
    Call<Void> deleteBus(@Path("id") int busId);
}
