package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelPickupPoint;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.PickupPointService;
import com.example.myapplication2.Service.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PickupPoint extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ModelPickupPoint> list;
    ImageView addPickupPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_point);

        recyclerView=findViewById(R.id.pickup_point_recycler_view);
        addPickupPoint=findViewById(R.id.add_pickup_point);

        list=new ArrayList<>();

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        PickupPointService pickupPointService =retrofit.create(PickupPointService.class);

        int routeId=getIntent().getIntExtra("routeId",1);

       // Toast.makeText(this,routeId+"",Toast.LENGTH_SHORT).show();

        Call<List<ModelPickupPoint>> call=pickupPointService.getAllPickupPoint(routeId);

        call.enqueue(new Callback<List<ModelPickupPoint>>() {
            @Override
            public void onResponse(Call<List<ModelPickupPoint>> call, Response<List<ModelPickupPoint>> response) {
                if(response.isSuccessful()){
                    //list.addAll(response.body());
                    //Toast.makeText(getApplicationContext(),list.get(0).getPickupPointName(),Toast.LENGTH_SHORT).show();
                    foo(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelPickupPoint>> call, Throwable t) {

            }
        });

        addPickupPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddPickupPoint.class);
                intent.putExtra("routeId",routeId);
                intent.putExtra("operation","add");
                startActivity(intent);
            }
        });
    }

    void foo(List<ModelPickupPoint> list){
        PickupPointAdapter pickupPointAdapter=new PickupPointAdapter(list,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pickupPointAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, Route.class));
    }
}