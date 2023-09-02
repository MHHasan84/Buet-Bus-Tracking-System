package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusPersonService;
import com.example.myapplication2.Service.RouteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddPerson extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView addPersonTopTv;
    EditText personNameEt;
    EditText personContactNoEt;
    Spinner personTypeSpn;
    EditText personDrivingLicenseEt;
    EditText personNidEt;
    Button personConfirmBtn;
    Button personCancelBtn;
    String[] types={"Driver","Helper"};
    String personType="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        addPersonTopTv=findViewById(R.id.add_person_top_tv);
        personNameEt=findViewById(R.id.person_name_et);
        personContactNoEt=findViewById(R.id.person_contact_no_et);
        personTypeSpn=findViewById(R.id.person_type_spn);
        personDrivingLicenseEt=findViewById(R.id.person_driving_license_et);
        personNidEt=findViewById(R.id.person_nid_et);
        personConfirmBtn=findViewById(R.id.person_confirm_btn);
        personCancelBtn=findViewById(R.id.person_cancel_btn);

        personTypeSpn.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personTypeSpn.setAdapter(ad);

        personConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBusPerson modelBusPerson=new ModelBusPerson();
                modelBusPerson.setName(personNameEt.getText().toString());
                modelBusPerson.setContactNumber(personContactNoEt.getText().toString());
                modelBusPerson.setBusPersonType(personType);
                modelBusPerson.setDrivingLicenseNo(personDrivingLicenseEt.getText().toString());
                modelBusPerson.setNidNo(personNidEt.getText().toString());

                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                BusPersonService busPersonService=retrofit.create(BusPersonService.class);

                Call<ModelBusPerson> call=busPersonService.addPerson(modelBusPerson);

                call.enqueue(new Callback<ModelBusPerson>() {
                    @Override
                    public void onResponse(Call<ModelBusPerson> call, Response<ModelBusPerson> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
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
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        personType=types[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}