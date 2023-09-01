package com.example.myapplication2.Repository;

import com.example.myapplication2.Model.ModelSchedule;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.RouteService;
import com.example.myapplication2.Service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositorySchedule {
    private static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private static ScheduleService scheduleService=retrofit.create(ScheduleService.class);
    public static List<ModelSchedule> getAllSchedule(int busId){
        List<ModelSchedule> list=new ArrayList<>();
        Call<List<ModelSchedule>> call=scheduleService.getAllSchedule(busId);
        call.enqueue(new Callback<List<ModelSchedule>>() {
            @Override
            public void onResponse(Call<List<ModelSchedule>> call, Response<List<ModelSchedule>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelSchedule>> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelSchedule> addSchedule(ModelSchedule modelSchedule){
        List<ModelSchedule> list=new ArrayList<>();
        Call<ModelSchedule> call= scheduleService.addSchedule(modelSchedule);
        call.enqueue(new Callback<ModelSchedule>() {
            @Override
            public void onResponse(Call<ModelSchedule> call, Response<ModelSchedule> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelSchedule> call, Throwable t) {

            }
        });
        return list;
    }
    public static List<ModelSchedule> editSchedule(ModelSchedule modelSchedule,int scheduleId){
        List<ModelSchedule> list=new ArrayList<>();
        Call<ModelSchedule> call= scheduleService.editSchedule(modelSchedule,scheduleId);
        call.enqueue(new Callback<ModelSchedule>() {
            @Override
            public void onResponse(Call<ModelSchedule> call, Response<ModelSchedule> response) {
                if(response.isSuccessful()){
                    list.add(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelSchedule> call, Throwable t) {

            }
        });
        return list;
    }
    public static void deleteSchedule(int scheduleId){
        Call<Void> call= scheduleService.deleteSchedule(scheduleId);
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
