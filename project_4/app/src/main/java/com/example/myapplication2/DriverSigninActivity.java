package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DriverSigninActivity extends AppCompatActivity {
    private EditText driverSigninIdEditText;
    private EditText driverSigninPasswordEditText;
    private Button driverSigninBtn;
    private String busNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_signin);

        driverSigninIdEditText=findViewById(R.id.driver_signin_id_et);
        driverSigninPasswordEditText=findViewById(R.id.driver_signin_password_et);
        driverSigninBtn=findViewById(R.id.driver_signin_btn);

        driverSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busNo=driverSigninIdEditText.getText().toString();
                Intent intent=new Intent(DriverSigninActivity.this, DriverHomepageActivity.class);
                intent.putExtra("busNo",busNo);
                startActivity(intent);
            }
        });
    }
}