package com.example.bustrackingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity {

    ScheduleAdapter scheduleAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        List<ScheduleModel> scheduleModelList=getData();
        recyclerView=findViewById(R.id.recyclerView);

        scheduleAdapter=new ScheduleAdapter(scheduleModelList,getApplication());
        recyclerView.setAdapter(scheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Schedule.this));
    }


    private List<ScheduleModel> getData(){
        List<ScheduleModel> scheduleModelList= new ArrayList<>();
        scheduleModelList.add(new ScheduleModel("bus1","pickup_point","9:30"));
        scheduleModelList.add(new ScheduleModel("bus1","pickup_point","9:30"));
        scheduleModelList.add(new ScheduleModel("bus1","pickup_point","9:30"));
        scheduleModelList.add(new ScheduleModel("bus1","pickup_point","9:30"));
        scheduleModelList.add(new ScheduleModel("bus1","pickup_point","9:30"));
        return scheduleModelList;
    }
}