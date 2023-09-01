package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
    private List<BusViewModel> busViewModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        recyclerView=findViewById(R.id.busRecyclerView);
        busViewModelList=new ArrayList<>();

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        List<ModelBus> modelBusList=new ArrayList<>();
        Call<List<ModelBus>> call= busService.getAllBus();
        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    modelBusList.addAll(response.body());
                    for(ModelBus modelBus:modelBusList){
                        busViewModelList.add(new BusViewModel(modelBus.getBusName(),R.drawable.bus_front));
                        foo(modelBus.getBusName());
                    }

                    BusViewAdapter busViewAdapter=new BusViewAdapter(busViewModelList,getApplicationContext());
                    GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);

                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(busViewAdapter);
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





    }

    void foo(String x){
        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();
    }
}