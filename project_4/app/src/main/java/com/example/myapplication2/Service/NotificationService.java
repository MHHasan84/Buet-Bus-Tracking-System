package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelNotification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NotificationService {
    @GET("notification/{id}")
    Call<List<ModelNotification>> getNotification(@Path("id") String receiverId);
    @POST("notification")
    Call<ModelNotification> addNotification(@Body ModelNotification modelNotification);
    @PUT("notification")
    Call<ModelNotification> updateNotification(@Body ModelNotification modelNotification);
}
