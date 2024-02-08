package com.example.online;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner chooseLocationSpinner;
    Button pushBtn;
    String[] locationArray={"location1","location2","location3","location4"};
    ArrayAdapter locationAdapter;
    String location;
    String chanelId = "online";
    int position=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseLocationSpinner = findViewById(R.id.choose_location_spn);
        pushBtn = findViewById(R.id.push_btn);

        locationAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, locationArray);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        chooseLocationSpinner.setAdapter(locationAdapter);

        chooseLocationSpinner.setOnItemSelectedListener(this);

        pushBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),location,Toast.LENGTH_SHORT);
                sendNotification();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        location = locationArray[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    void sendNotification() {
        notificationChannel();
        Notification nBuilder = new NotificationCompat.Builder(this, chanelId)

                // adding notification Title
                .setContentTitle("Bus Alert!!")

                // adding notification Text
                .setContentText("your bus is nearby "+location)

                // adding notification SmallIcon
                .setSmallIcon(R.drawable.bell)

                // adding notification Priority
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // making the notification clickable
                .setAutoCancel(true)

                // adding action button
                .build();
        // finally notifying the notification
        NotificationManagerCompat nManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        nManager.notify(1, nBuilder);
    }

    private void notificationChannel() {
        // check if the version is equal or greater
        // than android oreo version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // creating notification channel and setting
            // the description of the channel
            NotificationChannel channel =new NotificationChannel(chanelId, "online", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("online");

            // registering the channel to the System
            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}