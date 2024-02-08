package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.RouteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusViewAdapter extends RecyclerView.Adapter<BusViewHolder> {
    private List<ModelBus> busViewModelList;
    private Context context;

    public BusViewAdapter(List<ModelBus> busViewModelList, Context context) {
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
        ModelBus busViewModel=busViewModelList.get(position);
        holder.busRoute.setText("route");
        holder.busImage.setImageResource(R.drawable.bus_front);

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        RouteService routeService =retrofit.create(RouteService.class);

        Call<ModelRoute> call=routeService.getRoute(busViewModel.getRouteId());

        call.enqueue(new Callback<ModelRoute>() {
            @Override
            public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                if(response.isSuccessful()){
                    holder.busRoute.setText(response.body().getRouteName());
                }
            }

            @Override
            public void onFailure(Call<ModelRoute> call, Throwable t) {

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, BusDetails.class);
                intent.putExtra("busId",busViewModel.getBusId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return busViewModelList.size();
    }
}