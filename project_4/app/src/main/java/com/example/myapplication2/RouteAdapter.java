package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelRoute;

import java.util.List;

public class RouteAdapter extends RecyclerView.Adapter<RouteHolder> {
    List<ModelRoute> modelRouteList;
    Context context;

    public RouteAdapter(List<ModelRoute> modelRouteList, Context context) {
        this.modelRouteList = modelRouteList;
        this.context = context;
    }

    @NonNull
    @Override
    public RouteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View routeView=inflater.inflate(R.layout.route_view,parent,false);
        RouteHolder routeHolder=new RouteHolder(routeView);
        return routeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RouteHolder holder, int position) {
        int index=holder.getAdapterPosition();
        int routeId=modelRouteList.get(position).getRouteId();
        holder.routeNameView.setText(modelRouteList.get(position).getRouteName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),PickupPoint.class);
                intent.putExtra("routeId",routeId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelRouteList.size();
    }
}
