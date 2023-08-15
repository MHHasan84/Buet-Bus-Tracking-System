package com.example.bustrackingsystem;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScheduleHolder extends RecyclerView.ViewHolder {
    TextView pickupPoint;
    TextView pickupTime;
    View view;
    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        pickupPoint= itemView.findViewById(R.id.pickupPoint);
        pickupTime =itemView.findViewById(R.id.pickupTime);
        view=itemView;
    }
}
