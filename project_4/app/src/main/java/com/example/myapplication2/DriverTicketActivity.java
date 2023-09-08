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
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class DriverTicketActivity extends AppCompatActivity{
    private TextView messageText;
    private Button scanBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_ticket);

        messageText = findViewById(R.id.textContent);
        scanBtn=findViewById(R.id.scan_btn);

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult!=null){
            String content= intentResult.getContents();
            if(content!=null){
                messageText.setText(content);
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}