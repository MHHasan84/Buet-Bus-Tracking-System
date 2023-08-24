package com.example.myapplication2.RetrofitLearning;

import com.example.myapplication2.OpenWeather.City;
import com.example.myapplication2.OpenWeather.DailyForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataServiceHandler {
    public static void main(String[] args) {
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        DataServices dataServices=retrofit.create(DataServices.class);
        Call<DailyForecast> call=dataServices.getDailyForecast("Dhaka","a2987cfc5e6747b281fdeafbb25cb28e");

        call.enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
                if(response.isSuccessful()){
                    DailyForecast dailyForecast=response.body();
                }
            }

            @Override
            public void onFailure(Call<DailyForecast> call, Throwable t) {

            }
        });
    }
}
