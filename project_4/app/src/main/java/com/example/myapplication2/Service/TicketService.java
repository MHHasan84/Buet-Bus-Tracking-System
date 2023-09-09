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
    @GET("/ticket/{ticketId}")
    Call<ModelTicket> getTicket(@Path("ticketId") String ticketId);
    @POST("/ticket")
    Call<ModelTicket> addTicket(@Body ModelTicket ticket);
    @PUT("/ticket/{ticketId}")
    Call<ModelTicket> editTicket(@Body ModelTicket ticket,@Path("ticketId") String ticketId);
    @DELETE("/ticket/{ticketId}")
    Call<Void> deleteTicket(@Path("ticketId") String ticketId);
}
