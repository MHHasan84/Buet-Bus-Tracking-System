package com.example.myapplication2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RouteHolder extends RecyclerView.ViewHolder {
    TextView routeNameView;
    View view;
    public RouteHolder(@NonNull View itemView) {
        super(itemView);
        routeNameView=itemView.findViewById(R.id.route_name_view);
        view=itemView;
    }
}
