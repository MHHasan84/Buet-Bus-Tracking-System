package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class DriverHomepageActivity extends AppCompatActivity {
    CardView trackCv;
    CardView ticketCv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_homepage);

        trackCv=findViewById(R.id.track_cv);
        ticketCv=findViewById(R.id.ticket_cv);

        trackCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(new Intent(getApplicationContext(),DriverTrackActivity.class));
                startActivity(intent);
            }
        });

        ticketCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(new Intent(getApplicationContext(), DriverTicketActivity.class));
                startActivity(intent);
            }
        });
    }
}