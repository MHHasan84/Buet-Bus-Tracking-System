package com.example.myapplication2;

import com.example.myapplication2.Model.Location;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.osmdroid.util.GeoPoint;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BusRunThread implements Runnable{
    private List<GeoPoint> geoPointList;
    private String busNo;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference busReference;
    private DatabaseReference rootReference;
    private DatabaseReference locationReference;

    private Thread bus;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    private int index=0;

    public BusRunThread(List<GeoPoint> geoPointList, String busNo) {
        this.geoPointList = geoPointList;
        this.busNo = busNo;

        firebaseDatabase=FirebaseDatabase.getInstance();
        rootReference=firebaseDatabase.getReference();
        busReference=rootReference.child(busNo);
        locationReference=busReference.child("location");

        interval=2000;
    }

    public void start() {
        bus = new Thread(this);
        bus.start();
    }

    public void stop() {
        running.set(false);
    }

    void addLocation(GeoPoint geoPoint){
        String key=locationReference.push().getKey();
        Location location=new Location(key,geoPoint.getLatitude(),geoPoint.getLongitude());
        locationReference.child(key).setValue(location);
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get() && index<geoPointList.size()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
            // do something here
            addLocation(geoPointList.get(index));
            index++;
        }
    }
}
