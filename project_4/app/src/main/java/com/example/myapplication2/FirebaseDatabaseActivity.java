package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootReference;
    DatabaseReference studentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_database);

        firebaseDatabase=FirebaseDatabase.getInstance();
        rootReference=firebaseDatabase.getReference();

        studentReference=rootReference.child("student");
        studentReference.setValue("Hasan");
    }
}