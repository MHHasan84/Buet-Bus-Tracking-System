package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Repository.RepositoryBus;

import java.util.List;

public class AdminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        ImageView busesImage=findViewById(R.id.bus);
        ImageView routeImage=findViewById(R.id.route);


        busesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomePage.this, BusActivity.class);
                startActivity(intent);
            }
        });

        routeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHomePage.this,Route.class);
                startActivity(intent);
            }
        });
    }
}