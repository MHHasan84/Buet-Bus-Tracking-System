package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PickupPointHolder extends RecyclerView.ViewHolder {
    TextView pickupPointNameView;
    View view;
    public PickupPointHolder(@NonNull View itemView) {
        super(itemView);
        pickupPointNameView=itemView.findViewById(R.id.pickup_point_name_view);
        view=itemView;
    }
}
