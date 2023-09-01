package com.example.myapplication2.Repository;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.BusService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositoryBusPerson {
    private static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private static BusPersonService busPersonService=retrofit.create(BusPersonService.class);
    public static List<ModelBusPerson> addBusPerson(ModelBusPerson modelBusPerson){
        List<ModelBusPerson> list=new ArrayList<>();
        Call<ModelBusPerson> call=busPersonService.addPerson(modelBusPerson);
        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelBusPerson> getAllDriver(){
        List<ModelBusPerson> list=new ArrayList<>();
        Call<List<ModelBusPerson>> call=busPersonService.getAllDriver();
        call.enqueue(new Callback<List<ModelBusPerson>>() {
            @Override
            public void onResponse(Call<List<ModelBusPerson>> call, Response<List<ModelBusPerson>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelBusPerson>> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelBusPerson> getBusPerson(int busPersonId){
        List<ModelBusPerson> list=new ArrayList<>();
        Call<ModelBusPerson> call=busPersonService.getPerson(busPersonId);
        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelBusPerson> editBusPerson(ModelBusPerson modelBusPerson,int personId){
        List<ModelBusPerson> list=new ArrayList<>();
        Call<ModelBusPerson> call= busPersonService.editBusPerson(modelBusPerson,personId);
        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
        return list;
    }
    public static void deleteBusPerson(int busPersonId){
        Call<Void> call= busPersonService.deletePerson(busPersonId);
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
