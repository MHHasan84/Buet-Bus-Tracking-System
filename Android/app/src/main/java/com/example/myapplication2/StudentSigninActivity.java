package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentSigninActivity extends AppCompatActivity {
    private EditText studentSigninIdEditText;
    private EditText studentSigninPasswordEditText;
    private Button studentSigninBtn;
    private String studentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signin);

        studentSigninIdEditText=findViewById(R.id.student_signin_id_et);
        studentSigninPasswordEditText=findViewById(R.id.student_signin_password_et);
        studentSigninBtn=findViewById(R.id.student_signin_btn);

        studentSigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentId=studentSigninIdEditText.getText().toString();
                Intent intent=new Intent(StudentSigninActivity.this, StudentMain.class);
                intent.putExtra("studentId",studentId);
                startActivity(intent);
            }
        });
    }
}