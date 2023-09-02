package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.BusService;
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
    Button editPersonBtn;
    Button deletePersonBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        personNameTv=findViewById(R.id.person_details_name_tv);
        personContactNoTv=findViewById(R.id.person_details_contact_nmber_tv);
        personTypeTv=findViewById(R.id.person_details_type_tv);
        personDrivingLicenseTv=findViewById(R.id.person_details_driving_license_tv);
        personNidTv=findViewById(R.id.person_details_nid_tv);

        editPersonBtn=findViewById(R.id.edit_person_btn);
        deletePersonBtn=findViewById(R.id.delete_person_btn);

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

        editPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddPerson.class);
                intent.putExtra("operation","edit");
                intent.putExtra("personId",personId);
                startActivity(intent);
            }
        });

        deletePersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                BusPersonService busPersonService1=retrofit.create(BusPersonService.class);

                Call<Void> call1=busPersonService1.deletePerson(personId);

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
}