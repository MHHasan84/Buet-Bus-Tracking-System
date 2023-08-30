package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddBus extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] drivers={"driver1","driver2","driver3","driver4","driver5"};
    String[] helpers={"helper1","helper2","helper3","helper4","helper5"};
    String[] routes={"route1","route2","route3","route4","route5"};

    Spinner driverSpinner;
    Spinner helperSpinner;
    Spinner routeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus);

        driverSpinner=findViewById(R.id.driver_spinner);
        helperSpinner=findViewById(R.id.helper_spinner);
        routeSpinner=findViewById(R.id.route_spinner);

        driverSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter driverAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,drivers);
        driverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driverSpinner.setAdapter(driverAdapter);

        driverSpinner.setPrompt("choose driver");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}