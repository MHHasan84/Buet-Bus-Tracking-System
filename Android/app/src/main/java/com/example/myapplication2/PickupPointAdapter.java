package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelPickupPoint;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.PickupPointService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        ModelPickupPoint modelPickupPoint=pickupPointList.get(position);
        int pickupPointId=modelPickupPoint.getPickupPointId();
        String pickupPointName= modelPickupPoint.getPickupPointName();
        String pickupPointTime=modelPickupPoint.getTime();
        int routeId=modelPickupPoint.getRouteId();
        holder.pickupPointNameView.setText(pickupPointName);
        holder.pickupPointTimeView.setText(pickupPointTime);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.editPickupPointIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(), AddPickupPoint.class);
                intent.putExtra("operation","edit");
                intent.putExtra("pickupPointId",pickupPointId);
                intent.putExtra("pickupPointName",pickupPointName);
                intent.putExtra("pickupPointTime",pickupPointTime);
                intent.putExtra("routeId",routeId);
                context.startActivity(intent);
            }
        });

        holder.deletePickupPointIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                PickupPointService pickupPointService =retrofit.create(PickupPointService.class);

                Call<Void> call=pickupPointService.deletePickupPoint(pickupPointId);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context.getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context.getApplicationContext(),PickupPoint.class);
                            intent.putExtra("routeId",routeId);
                            context.startActivity(intent);
                        }
                        else{
                            Toast.makeText(context.getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return pickupPointList.size();
    }
}
