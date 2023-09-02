package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusService;
import com.example.myapplication2.Service.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Route extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ModelRoute> routeList;
    ImageView addRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        recyclerView=findViewById(R.id.roure_recycler_view);
        addRoute=findViewById(R.id.add_route);

        routeList=new ArrayList<>();

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        RouteService routeService =retrofit.create(RouteService.class);

        Call<List<ModelRoute>> call=routeService.getAllRoute();
        call.enqueue(new Callback<List<ModelRoute>>() {
            @Override
            public void onResponse(Call<List<ModelRoute>> call, Response<List<ModelRoute>> response) {
                if(response.isSuccessful()){
                    routeList.addAll(response.body());
                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                    foo(routeList);
                }
                else{
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelRoute>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        addRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddRoute.class);
                intent.putExtra("operation","add");
                startActivity(intent);
            }
        });

    }

    void foo(List<ModelRoute> routeList){
        RouteAdapter routeAdapter=new RouteAdapter(routeList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(routeAdapter);
    }
}