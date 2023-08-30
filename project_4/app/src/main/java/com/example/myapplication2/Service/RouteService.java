package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelRoute;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RouteService {
    @GET("route")
    Call<List<ModelRoute>> getAllRoute();
    @GET("route/{id}")
    Call<ModelRoute> getRoute(@Path("id") int routeId);
    @POST("route")
    Call<ModelRoute> addRoute(@Body ModelRoute modelRoute);
    @PUT("route/{id}")
    Call<ModelRoute> editRoute(@Body ModelRoute modelRoute,@Path("id") int routeId);
    @DELETE("route/{id}")
    Call<Void> deleteRoute(@Path("id") int routeId);
}
