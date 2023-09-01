package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.PickupPointService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonDetails extends AppCompatActivity {
    TextView personNameTv;
    TextView personContactNoTv;
    TextView personTypeTv;
    TextView personDrivingLicenseTv;
    TextView personNidTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        personNameTv=findViewById(R.id.person_details_name_tv);
        personContactNoTv=findViewById(R.id.person_details_contact_nmber_tv);
        personTypeTv=findViewById(R.id.person_details_type_tv);
        personDrivingLicenseTv=findViewById(R.id.person_details_driving_license_tv);
        personNidTv=findViewById(R.id.person_details_nid_tv);

        int personId=getIntent().getIntExtra("personId",1);

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusPersonService busPersonService=retrofit.create(BusPersonService.class);

        Call<ModelBusPerson> call=busPersonService.getPerson(personId);

        call.enqueue(new Callback<ModelBusPerson>() {
            @Override
            public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                    ModelBusPerson modelBusPerson=response.body();
                    personNameTv.setText(modelBusPerson.getName());
                    personContactNoTv.setText(modelBusPerson.getContactNumber());
                    personTypeTv.setText(modelBusPerson.getBusPersonType());
                    personDrivingLicenseTv.setText(modelBusPerson.getDrivingLicenseNo());
                    personNidTv.setText(modelBusPerson.getNidNo());
                }
                else{
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelBusPerson> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}