package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserWelcomeScreen extends AppCompatActivity {
    private Button loginTeacherId;
    private Button loginStudentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome_screen);

        loginStudentId=findViewById(R.id.login_student_id);

        loginStudentId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserWelcomeScreen.this, StudentLogin.class);
                startActivity(intent);
            }
        });


        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        DataService dataService=retrofit.create(DataService.class);

        Call<TestBus> call=dataService.getTestBus(2);
        call.enqueue(new Callback<TestBus>() {
            @Override
            public void onResponse(Call<TestBus> call, Response<TestBus> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"get bus successfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"get bus not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TestBus> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"get bus failed",Toast.LENGTH_SHORT).show();

            }
        });


    }
}