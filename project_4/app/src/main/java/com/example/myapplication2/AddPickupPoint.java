package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelPickupPoint;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.PickupPointService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPickupPoint extends AppCompatActivity {
    EditText pickupPointEditText;
    Button pickupPointConfirmBtn;
    TimePicker pickupPointTimePicker;
    TextView addPickupPointTopTv;
    Button pickupPointCancelBtn;
    int pickupPointId;
    int routeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pickup_point);

        String operation=getIntent().getStringExtra("operation");

        pickupPointEditText=findViewById(R.id.pickup_point_name_edit_text);
        pickupPointTimePicker=findViewById(R.id.pickup_point_time_picker);
        pickupPointConfirmBtn=findViewById(R.id.add_pickup_point_confirm_btn);
        addPickupPointTopTv=findViewById(R.id.add_pickup_point_top_tv);
        pickupPointCancelBtn=findViewById(R.id.add_pickup_point_cancel_btn);

        routeId=getIntent().getIntExtra("routeId",1);

        if(operation.equals("edit")){
            addPickupPointTopTv.setText("Edit Pickup Point");
            pickupPointId=getIntent().getIntExtra("pickupPointId",1);
            String pickupPointName=getIntent().getStringExtra("pickupPointName");
            pickupPointEditText.setText(pickupPointName);
            String time=getIntent().getStringExtra("pickupPointTime");
            if(time!=null){
                String[] strings=time.split(":");
                int hour=Integer.parseInt(strings[0]);
                int minute=Integer.parseInt(strings[1]);
                pickupPointTimePicker.setHour(hour);
                pickupPointTimePicker.setMinute(minute);
            }
        }

        pickupPointConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                PickupPointService pickupPointService =retrofit.create(PickupPointService.class);


                //int routeId=getIntent().getIntExtra("routeId2",1);

                // Toast.makeText(this,routeId+"",Toast.LENGTH_SHORT).show();

                String pickupPointName=pickupPointEditText.getText().toString();
                StringBuilder time=new StringBuilder();
                int hour=pickupPointTimePicker.getHour();
                int minute=pickupPointTimePicker.getMinute();
                if(hour<10){
                    time.append("0");
                }
                time.append(hour);
                time.append(":");
                if(minute<10){
                    time.append("0");
                }
                time.append(minute);
                ModelPickupPoint modelPickupPoint=new ModelPickupPoint();
                modelPickupPoint.setPickupPointName(pickupPointName);
                modelPickupPoint.setTime(time.toString());
                modelPickupPoint.setRouteId(routeId);

                if(operation.equals("add")){
                    Call<ModelPickupPoint> call=pickupPointService.addPickupPoint(modelPickupPoint);
                    call.enqueue(new Callback<ModelPickupPoint>() {
                        @Override
                        public void onResponse(Call<ModelPickupPoint> call, Response<ModelPickupPoint> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelPickupPoint> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else if(operation.equals("edit")){
                    Call<ModelPickupPoint> call=pickupPointService.editPickupPoint(modelPickupPoint,pickupPointId);
                    call.enqueue(new Callback<ModelPickupPoint>() {
                        @Override
                        public void onResponse(Call<ModelPickupPoint> call, Response<ModelPickupPoint> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelPickupPoint> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                changeActivity();
            }
        });

        pickupPointCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }

    void changeActivity(){
        Intent intent=new Intent(this, PickupPoint.class);
        intent.putExtra("routeId",routeId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changeActivity();
    }
}