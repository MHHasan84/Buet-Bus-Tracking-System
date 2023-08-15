package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserWelcomeScreen extends AppCompatActivity {
    private Button loginTeacherId;
    private Button loginStudentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome_screen);

        loginStudentId=findViewById(R.id.login_student_id);

        loginStudentId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserWelcomeScreen.this, StudentLogin.class);
                startActivity(intent);
            }
        });


    }
}