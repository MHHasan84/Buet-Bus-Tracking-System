package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentPickupPointHolder extends RecyclerView.ViewHolder {
    TextView studentPickupPointNameView;
    TextView studentPickupPointTimeView;
    public StudentPickupPointHolder(@NonNull View itemView) {
        super(itemView);

        studentPickupPointNameView=itemView.findViewById(R.id.student_pickup_point_name_view);
        studentPickupPointTimeView=itemView.findViewById(R.id.student_pickup_point_time_view);
    }
}
