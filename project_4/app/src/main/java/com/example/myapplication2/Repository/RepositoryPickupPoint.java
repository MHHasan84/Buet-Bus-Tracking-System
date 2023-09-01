package com.example.myapplication2.Repository;

import com.example.myapplication2.Model.ModelPickupPoint;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.PickupPointService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RepositoryPickupPoint {
    private static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private static PickupPointService pickupPointService=retrofit.create(PickupPointService.class);
    public static List<ModelPickupPoint> addPickupPoint(ModelPickupPoint modelPickupPoint){
        List<ModelPickupPoint> list=new ArrayList<>();
        Call<ModelPickupPoint> call= pickupPointService.addPickupPoint(modelPickupPoint);
        call.enqueue(new Callback<ModelPickupPoint>() {
            @Override
            public void onResponse(Call<ModelPickupPoint> call, Response<ModelPickupPoint> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelPickupPoint> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelPickupPoint> getAllPickupPoint(int routeId){
        List<ModelPickupPoint> list=new ArrayList<>();
        Call<List<ModelPickupPoint>> call= pickupPointService.getAllPickupPoint(routeId);
        call.enqueue(new Callback<List<ModelPickupPoint>>() {
            @Override
            public void onResponse(Call<List<ModelPickupPoint>> call, Response<List<ModelPickupPoint>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelPickupPoint>> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelPickupPoint> editPickupPoint(ModelPickupPoint modelPickupPoint,int pickupPointId){
        List<ModelPickupPoint> list=new ArrayList<>();
        Call<ModelPickupPoint> call= pickupPointService.editPickupPoint(modelPickupPoint,pickupPointId);
        call.enqueue(new Callback<ModelPickupPoint>() {
            @Override
            public void onResponse(Call<ModelPickupPoint> call, Response<ModelPickupPoint> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelPickupPoint> call, Throwable t) {

            }
        });
        return list;
    }
    public static void deletePickupPoint(int pickupPointId){
        Call<Void> call= pickupPointService.deletePickupPoint(pickupPointId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
