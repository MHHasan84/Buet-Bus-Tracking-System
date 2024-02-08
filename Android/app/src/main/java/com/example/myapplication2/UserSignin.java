package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserSignin extends AppCompatActivity {
    private EditText userSigninUsernameEt;
    private EditText userSigninPasswordEditText;
    private Button userSigninConfirmBtn;
    private Button userSigninCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signin);

        userSigninUsernameEt=findViewById(R.id.user_signin_username_et);
        userSigninPasswordEditText=findViewById(R.id.user_signin_password_et);
        userSigninConfirmBtn=findViewById(R.id.user_signin_confirm_btn);
        userSigninCancelBtn=findViewById(R.id.user_signin_cancel_btn);

        userSigninConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userSigninUsernameEt.getText().toString();
                String password=userSigninPasswordEditText.getText().toString();

                Intent intent=new Intent(getApplicationContext(), StudentMain.class);
                intent.putExtra("username",userName);
                startActivity(intent);
            }
        });

        userSigninCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), DriverHomepageActivity.class);
                intent.putExtra("username","hasan");
                startActivity(intent);
            }
        });
    }
}