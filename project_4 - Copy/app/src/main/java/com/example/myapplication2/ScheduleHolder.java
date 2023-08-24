package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;

public class ScheduleHolder extends RecyclerView.ViewHolder {
    TextView pickupPoint;
    TextView pickupTime;
    View view;
    public ScheduleHolder(@NonNull View itemView) {
        super(itemView);
        pickupPoint= itemView.findViewById(R.id.pickup_point);
        pickupTime =itemView.findViewById(R.id.pickup_time);
        view=itemView;
    }
}
