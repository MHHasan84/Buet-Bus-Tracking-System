package com.example.myapplication2.JsonPlaceholder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL="https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
