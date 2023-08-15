package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
    }

    public void sum(View view) {
        EditText ed1=findViewById(R.id.ed1);
        EditText ed2=findViewById(R.id.ed2);
        TextView tv1=findViewById(R.id.tv1);

        int num1=Integer.parseInt(ed1.getText().toString());
        int num2=Integer.parseInt(ed2.getText().toString());

        int sum=num1+num2;

        String output="Sum is "+sum;

        tv1.setText(output);


    }
}