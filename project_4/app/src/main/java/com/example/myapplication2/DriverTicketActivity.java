package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelTicket;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.TicketService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DriverTicketActivity extends AppCompatActivity{
    private Button scanBtn;
    private TextView ticketAvailableTv;
    private EditText numberOfTicketsExtractEt;
    private Button ticketExtractButton;
    private int numberOfTickets;
    private String ticketId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_ticket);

        scanBtn=findViewById(R.id.scan_btn);
        ticketAvailableTv=findViewById(R.id.ticket_available_tv);
        numberOfTicketsExtractEt=findViewById(R.id.number_of_tickets_extract_et);
        ticketExtractButton=findViewById(R.id.ticket_extract_btn);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator=new IntentIntegrator(DriverTicketActivity.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("scan qr code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
            }
        });

        ticketExtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int extract=Integer.parseInt(numberOfTicketsExtractEt.getText().toString());
                int ticketNumber=numberOfTickets-extract;
                updateTicket(ticketNumber);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
            String content= intentResult.getContents();
            if(content!=null){
                setTicketId(content);
                showTicketNumber(content);
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void showTicketNumber(String ticketId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        TicketService ticketService=retrofit.create(TicketService.class);

        Call<ModelTicket> call=ticketService.getTicket(ticketId);
        call.enqueue(new Callback<ModelTicket>() {
            @Override
            public void onResponse(Call<ModelTicket> call, Response<ModelTicket> response) {
                if(response.isSuccessful()){
                    ModelTicket modelTicket=response.body();
                    numberOfTickets=modelTicket.getNumberOfTickets();
                    ticketAvailableTv.setText(numberOfTickets+"");
                }
            }

            @Override
            public void onFailure(Call<ModelTicket> call, Throwable t) {

            }
        });
    }

    public void updateTicket(int ticketNumber){
        ModelTicket modelTicket=new ModelTicket(ticketId,ticketNumber,true);
        Retrofit retrofit=RetrofitInstance.getRetrofitInstance();
        TicketService ticketService=retrofit.create(TicketService.class);
        Call<ModelTicket> call=ticketService.editTicket(modelTicket,ticketId);
        call.enqueue(new Callback<ModelTicket>() {
            @Override
            public void onResponse(Call<ModelTicket> call, Response<ModelTicket> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"ticket extracted successfully",Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(Call<ModelTicket> call, Throwable t) {

            }
        });
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}