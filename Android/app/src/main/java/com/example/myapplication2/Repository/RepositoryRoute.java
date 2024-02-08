package com.example.myapplication2.Repository;


import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.PickupPointService;
import com.example.myapplication2.Service.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RepositoryRoute {
    private static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private static RouteService routeService=retrofit.create(RouteService.class);
    public static List<ModelRoute> addRoute(ModelRoute modelRoute){
        List<ModelRoute> list=new ArrayList<>();
        Call<ModelRoute> call= routeService.addRoute(modelRoute);
        call.enqueue(new Callback<ModelRoute>() {
            @Override
            public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelRoute> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelRoute> getAllRoute(){
        List<ModelRoute> list=new ArrayList<>();
        Call<List<ModelRoute>> call= routeService.getAllRoute();
        call.enqueue(new Callback<List<ModelRoute>>() {
            @Override
            public void onResponse(Call<List<ModelRoute>> call, Response<List<ModelRoute>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelRoute>> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelRoute> getRoute(int routeId){
        List<ModelRoute> list=new ArrayList<>();
        Call<ModelRoute> call= routeService.getRoute(routeId);
        call.enqueue(new Callback<ModelRoute>() {
            @Override
            public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelRoute> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelRoute> editRoute(ModelRoute modelRoute,int routeId){
        List<ModelRoute> list=new ArrayList<>();
        Call<ModelRoute> call= routeService.editRoute(modelRoute,routeId);
        call.enqueue(new Callback<ModelRoute>() {
            @Override
            public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelRoute> call, Throwable t) {

            }
        });
        return list;
    }
    public static void deleteRoute(int routeId){
        Call<Void> call= routeService.deleteRoute(routeId);
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
