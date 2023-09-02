package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.BusService;
import com.example.myapplication2.Service.PickupPointService;
import com.example.myapplication2.Service.RouteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusDetails extends AppCompatActivity {
    TextView busNumberTv;
    TextView busNameTv;
    TextView busTypeTv;
    TextView routeNameTv;
    TextView driverNameTv;
    TextView helperNameTv;
    Button editBusBtn;
    Button deleteBusBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        busNumberTv=findViewById(R.id.bus_number_tv);
        busNameTv=findViewById(R.id.bus_name_tv);
        busTypeTv=findViewById(R.id.bus_type_tv);
        routeNameTv=findViewById(R.id.route_name_tv);
        driverNameTv=findViewById(R.id.driver_name_tv);
        helperNameTv=findViewById(R.id.helper_name_tv);
        editBusBtn=findViewById(R.id.edit_bus_btn);
        deleteBusBtn=findViewById(R.id.delete_bus_btn);

        int busId=getIntent().getIntExtra("busId",1);

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        Call<ModelBus> call=busService.getBus(busId);

        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    ModelBus modelBus= response.body();
                    busNumberTv.setText(modelBus.getBusNumber());
                    busNameTv.setText(modelBus.getBusName());
                    busTypeTv.setText(modelBus.getBusType());
                    setRouteName(modelBus.getRouteId());
                    setDriverName(modelBus.getDriverId());
                    setHelperName(modelBus.getHelperId());
                }
            }

            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {

            }
        });

        editBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddBus.class);
                intent.putExtra("operation","edit");
                intent.putExtra("busId",busId);
                startActivity(intent);
            }
        });

        deleteBusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                BusService busService=retrofit.create(BusService.class);

                Call<Void> call1=busService.deleteBus(busId);

                call1.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }

    void setRouteName(int routeId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        RouteService routeService=retrofit.create(RouteService.class);
        Call<ModelRoute> call=routeService.getRoute(routeId);
        call.enqueue(new Callback<ModelRoute>() {
            @Override
            public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                if(response.isSuccessful()){
                    ModelRoute modelRoute=response.body();
                    routeNameTv.setText(modelRoute.getRouteName());
                }
            }

            @Override
            public void onFailure(Call<ModelRoute> call, Throwable t) {

            }
        });
    }
    void setDriverName(int driverId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusPersonService busPersonService=retrofit.create(BusPersonService.class);
        Call<ModelBusPerson> call=busPersonService.getPerson(driverId);
        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    ModelBusPerson modelBusPerson=response.body();
                    driverNameTv.setText(modelBusPerson.getName());
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
    }
    void setHelperName(int helperId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusPersonService busPersonService=retrofit.create(BusPersonService.class);
        Call<ModelBusPerson> call=busPersonService.getPerson(helperId);
        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    ModelBusPerson modelBusPerson=response.body();
                    helperNameTv.setText(modelBusPerson.getName());
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
    }
}