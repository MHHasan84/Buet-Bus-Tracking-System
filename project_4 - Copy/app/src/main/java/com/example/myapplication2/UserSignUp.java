package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserSignUp extends AppCompatActivity {
    private EditText userSignupUsername;
    private EditText userSignupEmail;
    private EditText userSignupPassword;
    private Button userSignupButton;
    private CheckBox adminRole;
    private CheckBox userRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        userSignupUsername=findViewById(R.id.user_signup_username);
        userSignupEmail=findViewById(R.id.user_signup_email);
        userSignupPassword=findViewById(R.id.user_signup_password);
        adminRole=findViewById(R.id.select_admin);
        userRole=findViewById(R.id.select_user);
        userSignupButton=findViewById(R.id.user_signup_btn);

        userSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userSignupUsername.getText().toString();
                String email=userSignupEmail.getText().toString();
                String password=userSignupPassword.getText().toString();
                List<String> role=new ArrayList<>();
                if(adminRole.isChecked()){
                    role.add(adminRole.getText().toString());
                }
                if(userRole.isChecked()){
                    role.add(userRole.getText().toString());
                }

                User user=new User();
                user.setUsername(userName);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(role);

                signup(user);
            }
        });
    }

    private void signup(User user){
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        DataService dataService=retrofit.create(DataService.class);

        Call<User> userCall=dataService.signUp(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"signup successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"signup does not successfully",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"signup failure",Toast.LENGTH_SHORT).show();

            }
        });

    }
}