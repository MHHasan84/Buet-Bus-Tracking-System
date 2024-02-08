package com.example.myapplication2.Repository;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

import android.content.Context;
import android.widget.Toast;

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
    private static Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
    private static BusService busService=retrofit.create(BusService.class);

    public static List<ModelBus> addBus(ModelBus modelBus){
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
    public static List<ModelBus> getAllBus(Context context){
        List<ModelBus> modelBusList=new ArrayList<>();
        Call<List<ModelBus>> call= busService.getAllBus();
        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    List<ModelBus> modelBusList2=response.body();

//                    Toast.makeText(context,"successfully",Toast.LENGTH_SHORT).show();
                    Toast.makeText(context,modelBusList.get(1).getBusName(),Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context,"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelBus>> call, Throwable t) {
                Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(context,modelBusList.get(0).getBusName(),Toast.LENGTH_SHORT).show();
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
    public static List<ModelBus> editBus(ModelBus modelBus){
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
    public static void deleteBus(int busId){
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
