package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication2.Model.ModelBusPerson;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.RouteService;

import retrofit2.Retrofit;

public class AddPerson extends AppCompatActivity {
    TextView addPersonTopTv;
    EditText personNameEt;
    EditText personContactNoEt;
    Spinner personTypeSpn;
    EditText personDrivingLicenseEt;
    EditText personNidEt;
    Button personConfirmBtn;
    Button personCancelBtn;
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

        personConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBusPerson modelBusPerson=new ModelBusPerson();
                modelBusPerson.setName(personNameEt.getText().toString());
                modelBusPerson.setContactNumber(personContactNoEt.getText().toString());
                modelBusPerson.setDrivingLicenseNo(personDrivingLicenseEt.getText().toString());
                modelBusPerson.setNidNo(personNidEt.getText().toString());
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                RouteService routeService =retrofit.create(RouteService.class);
            }
        });
    }
}