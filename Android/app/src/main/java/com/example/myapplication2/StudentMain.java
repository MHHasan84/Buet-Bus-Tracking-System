package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class StudentMain extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Fragment bus,home,notification,track,profile,contact,previous,current,ticket;
    private String studentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        studentId=getIntent().getStringExtra("studentId");

        home=new StudentHome();
        bus=new StudentBusFragment();
        notification=new Notification(studentId);
        track=new BusTrack();
        profile=new StudentProfile();
        contact=new ContactFragment();
        ticket=new StudentTicket(studentId);


        loadFragment(bus);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_home){
            current=home;
            loadFragment(bus);
            return true;
        }
        if(item.getItemId()==R.id.notification){
            current=notification;
            loadFragment(notification);
            return true;
        }
        if(item.getItemId()==R.id.track){
            loadFragment(track);
            return true;
        }
        if(item.getItemId()==R.id.profile){
            loadFragment(ticket);
            return true;
        }
        if(item.getItemId()==R.id.contact){
            loadFragment(contact);
            return true;
        }
        return false;
    }

    void loadFragment(Fragment fragment) {
        //to attach fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.rel1, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(current==home){
//            bottomNavigationView.setSelectedItemId(R.id.menu_home);
//        }
    }
}