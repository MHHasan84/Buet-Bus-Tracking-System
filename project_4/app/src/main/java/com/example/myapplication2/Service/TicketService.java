package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelTicket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TicketService {
    @GET("/ticket/{user_name}")
    Call<ModelTicket> getTicket(@Path("user_name") String userName);
    @POST("/ticket")
    Call<ModelTicket> addTicket(@Body ModelTicket ticket);
    @PUT("/ticket/{user_name}")
    Call<ModelTicket> editTicket(@Body ModelTicket ticket,@Path("user_name") String userName);
    @DELETE("/ticket/{user_name}")
    Call<Void> deleteTicket(@Path("user_name") String userName);
}
