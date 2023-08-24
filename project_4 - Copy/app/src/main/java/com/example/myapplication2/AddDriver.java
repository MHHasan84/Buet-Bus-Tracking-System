package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

public class AddDriver extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner roleSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        roleSelect=findViewById(R.id.role_select);
        roleSelect.setOnItemSelectedListener(this);

        String[] roles = new String[] {"Driver", "Helper"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        roleSelect.setAdapter(adapter);
        roleSelect.setPrompt("Select Role");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        roleSelect.setPrompt("Select Role");
    }
}