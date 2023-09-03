package com.example.myapplication2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PickupPointHolder extends RecyclerView.ViewHolder {
    TextView pickupPointNameView;
    TextView pickupPointTimeView;
    ImageView editPickupPointIv;
    ImageView deletePickupPointIv;
    View view;
    public PickupPointHolder(@NonNull View itemView) {
        super(itemView);
        pickupPointNameView=itemView.findViewById(R.id.pickup_point_name_view);
        pickupPointTimeView=itemView.findViewById(R.id.pickup_point_time_view);
        editPickupPointIv=itemView.findViewById(R.id.edit_pickup_point_iv);
        deletePickupPointIv=itemView.findViewById(R.id.delete_pickup_point_iv);
        view=itemView;
    }
}
