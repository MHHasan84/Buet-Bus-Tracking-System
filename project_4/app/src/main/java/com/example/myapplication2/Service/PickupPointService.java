package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelPickupPoint;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PickupPointService {
    @GET("pickup-point/{id}")
    Call<List<ModelPickupPoint>> getAllPickupPoint(int routeId);
    Call<ModelPickupPoint> addPickupPoint(@Body ModelPickupPoint modelPickupPoint);
    @PUT("pickup-point/{id}")
    Call<ModelPickupPoint> editPickupPoint(@Body ModelPickupPoint modelPickupPoint,@Path("id") int pickupPointId);
    @DELETE("pickup-point/{id}")
    Call<Void> deletePickupPoint(@Path("id") int pickupPointId);
}
