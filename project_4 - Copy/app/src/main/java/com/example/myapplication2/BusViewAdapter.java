package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BusViewAdapter extends RecyclerView.Adapter<BusViewHolder> {
    private List<BusViewModel> busViewModelList;
    private Context context;

    public BusViewAdapter(List<BusViewModel> busViewModelList, Context context) {
        this.busViewModelList = busViewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public BusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_view, parent, false);
        return new BusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusViewHolder holder, int position) {
        BusViewModel busViewModel=busViewModelList.get(position);
        holder.busRoute.setText(busViewModel.getRoute());
        holder.busImage.setImageResource(busViewModel.getImgId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Schedule.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busViewModelList.size();
    }
}
