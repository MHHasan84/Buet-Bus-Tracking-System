package com.example.myapplication2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        scheduleModelList.add(new ScheduleModel("bus1","College gate Tongi","6:15"));
        scheduleModelList.add(new ScheduleModel("bus1","Station Road","6:18"));
        scheduleModelList.add(new ScheduleModel("bus1","Tongi Bazar","6:20"));
        scheduleModelList.add(new ScheduleModel("bus1","AbdullahPur","6:22"));
        scheduleModelList.add(new ScheduleModel("bus1","HouseBuilding","6:25"));
        scheduleModelList.add(new ScheduleModel("bus1","BAF Shaheen College","6:35"));
        scheduleModelList.add(new ScheduleModel("bus1","Farmgate","6:40"));
        scheduleModelList.add(new ScheduleModel("bus1","Govt Science College","6:45"));

        return scheduleModelList;
    }
}