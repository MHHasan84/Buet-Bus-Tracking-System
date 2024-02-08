package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class DriverHomepageActivity extends AppCompatActivity {
    private CardView trackCv;
    private CardView ticketCv;
    private String busNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_homepage);

        trackCv=findViewById(R.id.track_cv);
        ticketCv=findViewById(R.id.ticket_cv);

        busNo=getIntent().getStringExtra("busNo");

        trackCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(new Intent(getApplicationContext(),DriverTrackActivity.class));
                intent.putExtra("busNo",busNo);
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