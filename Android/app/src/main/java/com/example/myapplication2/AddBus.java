package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.BusService;
import com.example.myapplication2.Service.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddBus extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText busNumberEt;
    EditText busNameEt;
    Spinner busTypeSpn;
    Spinner routeSpn;
    Spinner driverSpn;
    Spinner helperSpn;
    Button addBusConfirmBtn;
    Button addBusCancelBtn;
    TextView addBusTopTv;

    String[] busTypes={"student","teacher"};
    List<String> routeList=new ArrayList<>();
    List<String> driverList=new ArrayList<>();
    List<String> helperList=new ArrayList<>();

    List<ModelRoute> modelRouteList=new ArrayList<>();
    List<ModelBusPerson> modelDriverList=new ArrayList<>();
    List<ModelBusPerson> modelHelperList=new ArrayList<>();

    String busType;
    int routeId,driverId,helperId;

    int busId=1;

    ArrayAdapter busTypeAdapter,routeAdapter,driverAdapter,helperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        String operation=getIntent().getStringExtra("operation");


        busNumberEt=findViewById(R.id.bus_number_et);
        busNameEt=findViewById(R.id.bus_name_et);
        busTypeSpn=findViewById(R.id.bus_type_spn);
        routeSpn=findViewById(R.id.route_spn);
        driverSpn=findViewById(R.id.driver_spn);
        helperSpn=findViewById(R.id.helper_spn);

        addBusConfirmBtn=findViewById(R.id.add_bus_confirm_btn);
        addBusCancelBtn=findViewById(R.id.add_bus_cancel_btn);

        addBusTopTv=findViewById(R.id.add_bus_top_tv);

        busTypeSpn.setOnItemSelectedListener(this);
        routeSpn.setOnItemSelectedListener(this);
        driverSpn.setOnItemSelectedListener(this);
        helperSpn.setOnItemSelectedListener(this);

        busTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, busTypes);
        busTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        busTypeSpn.setAdapter(busTypeAdapter);
//
//
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);
        BusPersonService busPersonService=retrofit.create(BusPersonService.class);
        RouteService routeService=retrofit.create(RouteService.class);

        Call<List<ModelRoute>> call=routeService.getAllRoute();
        call.enqueue(new Callback<List<ModelRoute>>() {
            @Override
            public void onResponse(Call<List<ModelRoute>> call, Response<List<ModelRoute>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                    //modelRouteList.addAll(response.body());
                    setModelRouteList(response.body());
                }
                else{
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelRoute>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });
//
        Call<List<ModelBusPerson>> driverCall=busPersonService.getAllDriver();
        driverCall.enqueue(new Callback<List<ModelBusPerson>>() {
            @Override
            public void onResponse(Call<List<ModelBusPerson>> call, Response<List<ModelBusPerson>> response) {
                if(response.isSuccessful()){
                    setDriverList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelBusPerson>> call, Throwable t) {

            }
        });

        Call<List<ModelBusPerson>> helperCall=busPersonService.getAllHelper();
        helperCall.enqueue(new Callback<List<ModelBusPerson>>() {
            @Override
            public void onResponse(Call<List<ModelBusPerson>> call, Response<List<ModelBusPerson>> response) {
                if(response.isSuccessful()){
                    setHelperList(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelBusPerson>> call, Throwable t) {

            }
        });

        if(operation.equals("edit")){
            addBusTopTv.setText("Edit Bus");
            busId=getIntent().getIntExtra("busId",1);
            //Toast.makeText(getApplicationContext(),busId+"",Toast.LENGTH_SHORT).show();
            methodForEdit(busId);
        }

        addBusConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBus modelBus=new ModelBus();
                modelBus.setBusNumber(busNumberEt.getText().toString());
                modelBus.setBusName(busNameEt.getText().toString());
                modelBus.setBusType(busType);
                modelBus.setRouteId(routeId);
                modelBus.setDriverId(driverId);
                modelBus.setHelperId(helperId);

                if(operation.equals("add")){
                    Call<ModelBus> busCall=busService.addBus(modelBus);
                    busCall.enqueue(new Callback<ModelBus>() {
                        @Override
                        public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"bus added successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"bus not added successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelBus> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else if(operation.equals("edit")){
                    Call<ModelBus> busCall=busService.editBus(modelBus,busId);
                    busCall.enqueue(new Callback<ModelBus>() {
                        @Override
                        public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"bus edited successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"bus not edited successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelBus> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                changeActivity();
            }
        });

        addBusCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }

    void changeActivity(){
        startActivity(new Intent(this, BusActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changeActivity();
    }

    public void setModelRouteList(List<ModelRoute> modelRouteList) {
        for(ModelRoute modelRoute:modelRouteList){
            this.routeList.add(modelRoute.getRouteName());
        }
        routeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.routeList);
        routeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpn.setAdapter(routeAdapter);

        this.modelRouteList.addAll(modelRouteList);
    }

    public void setDriverList(List<ModelBusPerson> driverList) {
        for(ModelBusPerson modelBusPerson:driverList){
            this.driverList.add(modelBusPerson.getName());
        }
        driverAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.driverList);
        driverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driverSpn.setAdapter(driverAdapter);

        this.modelDriverList.addAll(driverList);
    }

    public void setHelperList(List<ModelBusPerson> helperList) {
        for(ModelBusPerson modelBusPerson:helperList){
            this.helperList.add(modelBusPerson.getName());
        }
        helperAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, this.helperList);
        helperAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        helperSpn.setAdapter(helperAdapter);

        this.modelHelperList.addAll(helperList);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId()==R.id.bus_type_spn){
            busType=busTypes[i];
        }
        if(adapterView.getId()==R.id.route_spn){
            routeId=modelRouteList.get(i).getRouteId();
        }
        if(adapterView.getId()==R.id.driver_spn){
            driverId=modelDriverList.get(i).getBusPersonId();
        }
        if(adapterView.getId()==R.id.helper_spn){
            helperId=modelHelperList.get(i).getBusPersonId();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    void methodForEdit(int busId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        Call<ModelBus> call=busService.getBus(busId);

        call.enqueue(new Callback<ModelBus>() {
            @Override
            public void onResponse(Call<ModelBus> call, Response<ModelBus> response) {
                if(response.isSuccessful()){
                    ModelBus modelBus= response.body();
                    busNumberEt.setText(modelBus.getBusNumber());
                    busNameEt.setText(modelBus.getBusName());
                    setBusType(modelBus.getBusType());
                    setRouteName(modelBus.getRouteId());
                    setDriverName(modelBus.getDriverId());
                    setHelperName(modelBus.getHelperId());
                }
            }

            @Override
            public void onFailure(Call<ModelBus> call, Throwable t) {

            }
        });
    }

    void setBusType(String busType){
        int spinnerPosition = busTypeAdapter.getPosition(busType);
        busTypeSpn.setSelection(spinnerPosition);
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
                    int spinnerPosition = routeAdapter.getPosition(modelRoute.getRouteName());
                    routeSpn.setSelection(spinnerPosition);
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
                    int spinnerPosition = driverAdapter.getPosition(modelBusPerson.getName());
                    driverSpn.setSelection(spinnerPosition);
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
                    int spinnerPosition = helperAdapter.getPosition(modelBusPerson.getName());
                    helperSpn.setSelection(spinnerPosition);
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {

            }
        });
    }
}
