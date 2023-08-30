package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NearbyBusAdapter extends RecyclerView.Adapter<NearbyBusHolder> {
    private List<NearbyBus> nearbyBusList;
    private Context context;

    public NearbyBusAdapter(List<NearbyBus> nearbyBusList, Context context) {
        this.nearbyBusList = nearbyBusList;
        this.context = context;
    }

    @NonNull
    @Override
    public NearbyBusHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_bus_cardview, parent, false);
        return new NearbyBusHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyBusHolder holder, int position) {
        NearbyBus nearbyBus=nearbyBusList.get(position);
        holder.arrivalTime.setText(nearbyBus.getArrivalTime());
        holder.currentLocation.setText(nearbyBus.getCurrentLocation());
    }

    @Override
    public int getItemCount() {
        return nearbyBusList.size();
    }
}
