package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelPickupPoint;

import java.util.List;

public class StudentPickupPointAdapter extends RecyclerView.Adapter<StudentPickupPointHolder> {
    List<ModelPickupPoint> pickupPointList;
    Context context;

    public StudentPickupPointAdapter(List<ModelPickupPoint> pickupPointList, Context context) {
        this.pickupPointList = pickupPointList;
        this.context = context;
    }
    @NonNull
    @Override
    public StudentPickupPointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View pickupPointView=inflater.inflate(R.layout.student_pickup_point_view,parent,false);
        StudentPickupPointHolder studentPickupPointHolder=new StudentPickupPointHolder(pickupPointView);
        return studentPickupPointHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentPickupPointHolder holder, int position) {
        int index=holder.getAdapterPosition();
        ModelPickupPoint modelPickupPoint=pickupPointList.get(position);
        int pickupPointId=modelPickupPoint.getPickupPointId();
        String pickupPointName= modelPickupPoint.getPickupPointName();
        String pickupPointTime=modelPickupPoint.getTime();
        int routeId=modelPickupPoint.getRouteId();
        holder.studentPickupPointNameView.setText(pickupPointName);
        holder.studentPickupPointTimeView.setText(pickupPointTime);
    }

    @Override
    public int getItemCount() {
        return pickupPointList.size();
    }
}
