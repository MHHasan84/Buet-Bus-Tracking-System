package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class StudentHomepage extends AppCompatActivity {
    private RecyclerView nearbyBus;
    private RecyclerView schedule;
    private List<NearbyBus> nearbyBusList;
    private List<ScheduleModel> scheduleModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);

        nearbyBus=findViewById(R.id.nearby_bus);
        schedule=findViewById(R.id.schedule_recycler);

        nearbyBusList=new ArrayList<>();
        scheduleModelList=getData();

        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));

        NearbyBusAdapter nearbyBusAdapter=new NearbyBusAdapter(nearbyBusList,this);
        LinearLayoutManager nearbyBusLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        nearbyBus.setLayoutManager(nearbyBusLayoutManager);
        nearbyBus.setAdapter(nearbyBusAdapter);

        ScheduleAdapter scheduleAdapter=new ScheduleAdapter(scheduleModelList,this);
        LinearLayoutManager scheduleLayoutManager=new LinearLayoutManager(this);

        schedule.setLayoutManager(scheduleLayoutManager);
        schedule.setAdapter(scheduleAdapter);
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