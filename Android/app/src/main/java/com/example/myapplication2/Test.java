package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Test extends AppCompatActivity {

    private TextView testTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testTv=findViewById(R.id.test_tv);

        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        DataService dataService=retrofit.create(DataService.class);

//        Call<TestBus> call=dataService.getTestBus(1);
//        call.enqueue(new Callback<TestBus>() {
//            @Override
//            public void onResponse(Call<TestBus> call, Response<TestBus> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),"get bus successfully",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"get bus not successfully",Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TestBus> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"get bus failed",Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        User user=new User();
//        user.setUsername("toufikhasan");
//        user.setPassword("123456");
//
//        Call<User> call=dataService.signIn(user);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
//            }
//        });

        Call<Signout> call=dataService.signOut();
        call.enqueue(new Callback<Signout>() {
            @Override
            public void onResponse(Call<Signout> call, Response<Signout> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Signout> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}