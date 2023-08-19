package com.example.myapplication2.RetrofitLearning;

import com.example.myapplication2.OpenWeather.DailyForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataServices {

    //end url:data/2.5/weather?q=London,uk&APPID=a2987cfc5e6747b281fdeafbb25cb28e

    @GET("data/2.5/weather")
    Call<DailyForecast> getDailyForecast(@Query("q") String city,@Query("APPID") String appid);

    @GET("data/{id}/weather")
    Call<DailyForecast> getDailyForecast2(@Query("q") String city, @Query("APPID") String appid, @Path("id") int id);
}
