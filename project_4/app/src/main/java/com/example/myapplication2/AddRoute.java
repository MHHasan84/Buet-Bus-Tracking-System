package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelRoute;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.RouteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddRoute extends AppCompatActivity {
    EditText routeNameEditText;
    Button addRouteConformBtn;
    Button addRouteCancelBtn;
    TextView addRouteTopTv;
    int routeId=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        routeNameEditText=findViewById(R.id.route_name_edit_text);
        addRouteConformBtn=findViewById(R.id.add_route_confirm_btn);
        addRouteTopTv=findViewById(R.id.add_route_top_tv);
        addRouteCancelBtn=findViewById(R.id.add_route_cancel_btn);

        String operation=getIntent().getStringExtra("operation");

        if(operation.equals("edit")){
            addRouteTopTv.setText("Edit Route");
            routeId=getIntent().getIntExtra("routeId",1);
            String routeName=getIntent().getStringExtra("routeName");
            routeNameEditText.setText(routeName);
        }

        addRouteConformBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
                RouteService routeService =retrofit.create(RouteService.class);

                String routeName=routeNameEditText.getText().toString();
                ModelRoute modelRoute=new ModelRoute();
                modelRoute.setRouteName(routeName);
                if(operation.equals("add")){
                    Call<ModelRoute> call=routeService.addRoute(modelRoute);
                    call.enqueue(new Callback<ModelRoute>() {
                        @Override
                        public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelRoute> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else if(operation.equals("edit")){
                    Call<ModelRoute> call=routeService.editRoute(modelRoute,routeId);
                    call.enqueue(new Callback<ModelRoute>() {
                        @Override
                        public void onResponse(Call<ModelRoute> call, Response<ModelRoute> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"not successfully",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelRoute> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                changeActivity();
            }
        });

        addRouteCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity();
            }
        });
    }

    void changeActivity(){
        startActivity(new Intent(this, Route.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        changeActivity();
    }
}