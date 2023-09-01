package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button adminButton=(Button) findViewById(R.id.adminbtn);
        Button userButton=findViewById(R.id.userbtn);
        TextView createAccountTv=findViewById(R.id.create_account_tv);

        TextView err=findViewById(R.id.errTv);

        System.out.println("Hasan");

//        userButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, UserWelcomeScreen.class);
//                startActivity(intent);
//            }
//        });
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        DataService dataService=retrofit.create(DataService.class);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AdminLogin.class);
                startActivity(intent);
//                Call<List<TestLocalhostBus>> call=dataService.getAllBus();
//                call.enqueue(new Callback<List<TestLocalhostBus>>() {
//                    @Override
//                    public void onResponse(Call<List<TestLocalhostBus>> call, Response<List<TestLocalhostBus>> response) {
//                        if(response.isSuccessful()){
//                            Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
//                            err.setText(response.body().get(0).getBusname());
//                        }
//                        else{
//                            Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<List<TestLocalhostBus>> call, Throwable t) {
//                        //Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
//                        err.setText(t.toString());
//                    }
//                });
            }
        });

        createAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UserSignUp.class);
                startActivity(intent);
            }
        });


//        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
//        DataService dataService=retrofit.create(DataService.class);

//        Call<Signout> call=dataService.signOut();
//        call.enqueue(new Callback<Signout>() {
//            @Override
//            public void onResponse(Call<Signout> call, Response<Signout> response) {
//                if(response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Signout> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
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
//                    User user1=response.body();
//                    Toast.makeText(getApplicationContext(),user1.getUsername(),Toast.LENGTH_SHORT).show();
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


//        Call<TestBus> call2=dataService.getTestBus(1);
//        call2.enqueue(new Callback<TestBus>() {
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

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Track> call1=dataService.getTrack(7);
                call1.enqueue(new Callback<Track>() {
                    @Override
                    public void onResponse(Call<Track> call, Response<Track> response) {
                        if(response.isSuccessful()){
                            Track track=response.body();
                            Toast.makeText(getApplicationContext(),track.getLatitude().toString(),Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Track> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}