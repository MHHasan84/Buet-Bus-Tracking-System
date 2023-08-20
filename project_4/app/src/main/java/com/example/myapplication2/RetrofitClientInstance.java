package com.example.myapplication2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static final String BASE_URL="https://userservicebuetbus.azurewebsites.net/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
