package com.example.myapplication2.JsonPlaceholder;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataServiceHandler extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.json_placeholder);
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        DataServices dataServices=retrofit.create(DataServices.class);
        Call<Post> call=dataServices.getAPost(1);

        TextView postView=findViewById(R.id.post_view);


        call.enqueue(new Callback<Post>() {

            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post post=response.body();
                    postView.setText(post.toString());
                }
                else{
                    System.out.println("Hasan");
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                System.out.println("Hasan2");
            }
        });
    }
}
