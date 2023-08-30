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
    static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    static BusService busService=retrofit.create(BusService.class);
    public static String addBus(ModelBus modelBus){
        StringBuilder responseMsg=new StringBuilder();
        Call<ModelBus> call= busService.addBus(modelBus);
        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    responseMsg.append("bus added successfully");
                }
                else{
                    responseMsg.append("bus is not added successfully");
                }
            }
            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {
                responseMsg.append("bus addition failed");
            }
        });
        return responseMsg.toString();
    }
    public static List<ModelBus> getAllBus(){
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
    public static List<ModelBus> getBus(int busId){
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
    public static boolean editBus(ModelBus modelBus){
        return false;
    }
    public static boolean deleteBus(int busId){
        return false;
    }
}
