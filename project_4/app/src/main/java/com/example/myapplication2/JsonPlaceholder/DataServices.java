package com.example.myapplication2.JsonPlaceholder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataServices {
    @GET("posts/{id}")
    Call<Post> getAPost(@Path("id") int id);
}
