package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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


    }
}