package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleHolder>{

    List<ScheduleModel> scheduleList;
    Context context;

    public ScheduleAdapter(List<ScheduleModel> scheduleList, Context context) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View scheduleView=inflater.inflate(R.layout.schedule_view,parent,false);
        ScheduleHolder scheduleHolder=new ScheduleHolder(scheduleView);
        return scheduleHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        int index=holder.getAdapterPosition();
        holder.pickupPoint.setText(scheduleList.get(position).getPickupPoint());
        holder.pickupTime.setText(scheduleList.get(position).getPickupTime());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

}
