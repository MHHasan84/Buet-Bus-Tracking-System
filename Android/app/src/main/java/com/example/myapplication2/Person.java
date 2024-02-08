package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.RouteService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Person extends AppCompatActivity {
    ImageView addPersonIv;
    RecyclerView personRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        addPersonIv=findViewById(R.id.add_person_iv);
        personRecyclerView=findViewById(R.id.person_recycler_view);

        List<ModelBusPerson> modelBusPersonList=new ArrayList<>();

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusPersonService busPersonService =retrofit.create(BusPersonService.class);

        Call<List<ModelBusPerson>> call=busPersonService.getAllPerson();

        call.enqueue(new Callback<List<ModelBusPerson>>() {
            @Override
            public void onResponse(Call<List<ModelBusPerson>> call, Response<List<ModelBusPerson>> response) {
                if(response.isSuccessful()){

                    Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();

                    modelBusPersonList.addAll(response.body());

                    foo(modelBusPersonList);

                }
                else{
                    Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelBusPerson>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });

        addPersonIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AddPerson.class);
                intent.putExtra("operation","add");
                startActivity(intent);
            }
        });
    }

    void foo(List<ModelBusPerson> modelBusPersonList){
        PersonAdapter personAdapter=new PersonAdapter(modelBusPersonList,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//
        personRecyclerView.setLayoutManager(linearLayoutManager);
        personRecyclerView.setAdapter(personAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, AdminHomePage.class));
    }
}