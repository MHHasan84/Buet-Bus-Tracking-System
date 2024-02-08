package com.example.myapplication2.Service;

import com.example.myapplication2.Model.ModelStudent;
import com.example.myapplication2.Model.ModelTicket;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {
    @GET("/student/{studentId}")
    Call<ModelStudent> getStudent(@Path("studentId") String studentId);
    @POST("/student")
    Call<ModelStudent> addStudent(@Body ModelStudent student);
    @PUT("/student/{studentId}")
    Call<ModelStudent> editStudent(@Body ModelStudent student,@Path("studentId") String studentId);
    @DELETE("/student/{studentId}")
    Call<Void> deleteStudent(@Path("studentId") String studentId);
}
