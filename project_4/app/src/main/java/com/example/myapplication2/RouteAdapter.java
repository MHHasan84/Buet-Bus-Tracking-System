package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.RouteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        String routeName=modelRouteList.get(position).getRouteName();
        holder.routeNameView.setText(routeName);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),PickupPoint.class);
                intent.putExtra("routeId",routeId);
                context.startActivity(intent);
            }
        });

        holder.editRouteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(), AddRoute.class);
                intent.putExtra("operation","edit");
                intent.putExtra("routeId",routeId);
                intent.putExtra("routeName",routeName);
                context.startActivity(intent);
            }
        });

        holder.deleteRouteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                RouteService routeService =retrofit.create(RouteService.class);

                Call<Void> call=routeService.deleteRoute(routeId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context.getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
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
        return modelRouteList.size();
    }
}
