package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NearbyBusHolder extends RecyclerView.ViewHolder {
    protected TextView arrivalTime;
    protected TextView currentLocation;
    public NearbyBusHolder(@NonNull View itemView) {
        super(itemView);
        arrivalTime=itemView.findViewById(R.id.arrival_time);
        currentLocation=itemView.findViewById(R.id.current_location);
    }


}
