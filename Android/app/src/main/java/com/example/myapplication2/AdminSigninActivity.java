package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminSigninActivity extends AppCompatActivity {
    private EditText adminSigninIdEditText;
    private EditText adminSigninPasswordEditText;
    private Button adminSigninBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);

        adminSigninIdEditText=findViewById(R.id.admin_signin_id_et);
        adminSigninPasswordEditText=findViewById(R.id.admin_signin_password_et);
        adminSigninBtn=findViewById(R.id.admin_signin_btn);

        adminSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminSigninActivity.this, AdminHomePage.class);
                startActivity(intent);
            }
        });
    }
}