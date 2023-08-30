package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelBusPerson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BusPersonService {
    @GET("driver")
    Call<List<ModelBusPerson>> getAllDriver();
    @GET("helper")
    Call<List<ModelBusPerson>> getAllHelper();
    @GET("person/{id}")
    Call<ModelBusPerson> getPerson(@Path("id") int personId);
    @POST("person")
    Call<ModelBusPerson> addPerson(@Body ModelBusPerson modelBusPerson);
    @PUT("person/{id}")
    Call<ModelBusPerson> editBusPerson(@Body ModelBusPerson modelBusPerson,@Path("id") int personId);
    @DELETE("person/{id}")
    Call<Void> deletePerson(@Path("id") int personId);
}
