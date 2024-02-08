package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelSchedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ScheduleService {
    @GET("schedule/{id}")
    Call<List<ModelSchedule>> getAllSchedule(@Path("id") int busId);
    @POST("schedule")
    Call<ModelSchedule> addSchedule(@Body ModelSchedule modelSchedule);
    @PUT("schedule/{id}")
    Call<ModelSchedule> editSchedule(@Body ModelSchedule modelSchedule,@Path("id") int scheduleId);
    @DELETE("schedule/{id}")
    Call<Void> deleteSchedule(@Path("id") int scheduleId);
}
