package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.myapplication2.Model.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

public class DriverTrackActivity extends AppCompatActivity {

    private EditText fromLatEt,fromLonEt,toLatEt,toLonEt;
    private Button roadSetBtn,roadStartBtn,roadStopBtn;
    private SeekBar roadSeekBar;
    private TextView showPosTv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootReference;
    DatabaseReference locationReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_track);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        firebaseDatabase=FirebaseDatabase.getInstance();
        rootReference=firebaseDatabase.getReference();
        locationReference=rootReference.child("location");

        fromLatEt=findViewById(R.id.from_lat_et);
        fromLonEt=findViewById(R.id.from_lon_et);
        toLatEt=findViewById(R.id.to_lat_et);
        toLonEt=findViewById(R.id.to_lon_et);
        roadSetBtn=findViewById(R.id.road_set_btn);
        roadSeekBar=findViewById(R.id.road_seek_bar);
        showPosTv=findViewById(R.id.show_pos_tv);
        roadStartBtn=findViewById(R.id.road_start_btn);
        roadStopBtn=findViewById(R.id.road_stop_btn);

        roadSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                double fromLat=Double.parseDouble(fromLatEt.getText().toString());
//                double fromLon=Double.parseDouble(fromLonEt.getText().toString());
//                double toLat=Double.parseDouble(toLatEt.getText().toString());
//                double toLon=Double.parseDouble(toLonEt.getText().toString());

                double fromLat=23.782668;
                double fromLon=90.398880;
                double toLat=23.812500;
                double toLon=90.405301;

                GeoPoint fromPoint=new GeoPoint(fromLat,fromLon);
                GeoPoint toPoint=new GeoPoint(toLat,toLon);

                List<GeoPoint> geoPointList=getAllPoint(fromPoint,toPoint);
//
                setSeekBar(geoPointList);
            }
        });

        roadStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        roadStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    List<GeoPoint> getAllPoint(GeoPoint fromPoint,GeoPoint toPoint){
        ArrayList<GeoPoint> line=new ArrayList<>();
        line .add(fromPoint);
        line .add(toPoint);

        RoadManager roadManager = new OSRMRoadManager(this, "OBP_Tuto/1.0");

        Road road = roadManager.getRoad(line);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);

        List<GeoPoint> points=roadOverlay.getPoints();
        return points;
    }

    void showPoint(GeoPoint geoPoint){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(geoPoint.getLatitude());
        stringBuilder.append(", ");
        stringBuilder.append(geoPoint.getLongitude());
        showPosTv.setText(stringBuilder.toString());
    }

    void setSeekBar(List<GeoPoint> geoPointList){
        int min=0;
        int max=geoPointList.size();
        int current=0;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            roadSeekBar.setMin(min);
            roadSeekBar.setMax(max);
        }

        // Set the current to progress
        // and display in the TextView
        //roadSeekBar.setProgress(current);
        showPoint(geoPointList.get(current));

        // On Change Listener to change
        // current values as drag
        roadSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                showPoint(geoPointList.get(i));
                addLocation(geoPointList.get(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    void addLocation(GeoPoint geoPoint){
        String key=locationReference.push().getKey();
        Location location=new Location(key,geoPoint.getLatitude(),geoPoint.getLongitude());
        locationReference.child(key).setValue(location);

//        Location2 location2=new Location2(key,geoPoint.getLatitude()+"",geoPoint.getLongitude()+"");
//        locationReference.child(key).setValue(location2);
    }
}