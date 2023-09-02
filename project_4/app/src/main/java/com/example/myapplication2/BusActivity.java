package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Repository.RepositoryBus;
import com.example.myapplication2.Repository.RepositoryRoute;
import com.example.myapplication2.Service.BusService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ImageView addBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        recyclerView=findViewById(R.id.busRecyclerView);
        addBus=findViewById(R.id.add_bus);


        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        List<ModelBus> modelBusList=new ArrayList<>();
        Call<List<ModelBus>> call= busService.getAllBus();
        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    modelBusList.addAll(response.body());

                    foo(modelBusList);

                }
                else{
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelBus>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });


        addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddBus.class);
                intent.putExtra("operation","add");
                startActivity(intent);
            }
        });





    }

    void foo(List<ModelBus> modelBusList){
        BusViewAdapter busViewAdapter=new BusViewAdapter(modelBusList,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(busViewAdapter);
    }
}