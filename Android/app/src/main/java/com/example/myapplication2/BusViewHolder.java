package com.example.myapplication2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.R;

public class BusViewHolder extends RecyclerView.ViewHolder {
    public TextView busRoute;
    public ImageView busImage;
    public BusViewHolder(@NonNull View itemView) {
        super(itemView);
        busRoute=itemView.findViewById(R.id.busRoute);
        busImage=itemView.findViewById(R.id.busImage);
    }
}
