package com.example.myapplication2.Repository;

import com.example.myapplication2.DataService;
import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.RetrofitClientInstance;
import com.example.myapplication2.Service.BusService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryBus {
    private Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private BusService busService=retrofit.create(BusService.class);
    public List<ModelBus> addBus(ModelBus modelBus){
        List<ModelBus> modelBusList=new ArrayList<>();
        Call<ModelBus> call= busService.addBus(modelBus);
        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    modelBusList.add(response.body());
                }

            }
            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {

            }
        });
        return modelBusList;
    }
    public List<ModelBus> getAllBus(){
        List<ModelBus> modelBusList=new ArrayList<>();
        Call<List<ModelBus>> call= busService.getAllBus();
        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    modelBusList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelBus>> call, Throwable t) {

            }
        });
        return modelBusList;
    }
    public List<ModelBus> getBus(int busId){
        List<ModelBus> modelBusList=new ArrayList<>();
        Call<ModelBus> call= busService.getBus(busId);
        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    modelBusList.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {

            }
        });
        return modelBusList;
    }
    public List<ModelBus> editBus(ModelBus modelBus){
        List<ModelBus> modelBusList=new ArrayList<>();
        Call<ModelBus> call= busService.editBus(modelBus, modelBus.getBusId());
        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    modelBusList.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {

            }
        });
        return modelBusList;
    }
    public void deleteBus(int busId){
        Call<Void> call= busService.deleteBus(busId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    return;
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
