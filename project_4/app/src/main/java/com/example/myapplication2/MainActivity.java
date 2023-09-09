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
        Button driverButton=findViewById(R.id.driverbtn);


        driverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, DriverSigninActivity.class);
                startActivity(intent);
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, UserWelcomeScreen.class);
                Intent intent=new Intent(MainActivity.this, StudentSigninActivity.class);
                startActivity(intent);
            }
        });
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AdminSigninActivity.class);
                startActivity(intent);
            }
        });

    }
}