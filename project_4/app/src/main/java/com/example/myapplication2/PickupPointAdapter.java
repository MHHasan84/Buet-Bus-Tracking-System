package com.example.myapplication2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelPickupPoint;

import java.util.List;

public class PickupPointAdapter extends RecyclerView.Adapter<PickupPointHolder> {
    List<ModelPickupPoint> pickupPointList;
    Context context;

    public PickupPointAdapter(List<ModelPickupPoint> pickupPointList, Context context) {
        this.pickupPointList = pickupPointList;
        this.context = context;
    }

    @NonNull
    @Override
    public PickupPointHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View pickupPointView=inflater.inflate(R.layout.pickup_point_view,parent,false);
        PickupPointHolder pickupPointHolder=new PickupPointHolder(pickupPointView);
        return pickupPointHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PickupPointHolder holder, int position) {
        int index=holder.getAdapterPosition();
        holder.pickupPointNameView.setText(pickupPointList.get(position).getPickupPointName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return pickupPointList.size();
    }
}
