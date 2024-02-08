package com.example.myapplication2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RouteHolder extends RecyclerView.ViewHolder {
    TextView routeNameView;
    ImageView editRouteIv;
    ImageView deleteRouteIv;
    View view;
    public RouteHolder(@NonNull View itemView) {
        super(itemView);
        routeNameView=itemView.findViewById(R.id.route_name_view);
        editRouteIv=itemView.findViewById(R.id.edit_route_iv);
        deleteRouteIv=itemView.findViewById(R.id.delete_route_iv);
        view=itemView;
    }
}
