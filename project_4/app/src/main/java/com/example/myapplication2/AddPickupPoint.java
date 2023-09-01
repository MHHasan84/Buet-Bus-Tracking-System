package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pickup_point);

        pickupPointEditText=findViewById(R.id.pickup_point_name_edit_text);
        pickupPointConfirmBtn=findViewById(R.id.add_pickup_point_confirm_btn);





        pickupPointConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                PickupPointService pickupPointService =retrofit.create(PickupPointService.class);

                int routeId=getIntent().getIntExtra("routeId2",1);

                // Toast.makeText(this,routeId+"",Toast.LENGTH_SHORT).show();

                String pickupPointName=pickupPointEditText.getText().toString();
                ModelPickupPoint modelPickupPoint=new ModelPickupPoint();
                modelPickupPoint.setPickupPointName(pickupPointName);
                modelPickupPoint.setRouteId(routeId);
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
        });
    }
}