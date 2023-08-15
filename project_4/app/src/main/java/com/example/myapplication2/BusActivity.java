package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class BusActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<BusViewModel> busViewModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        recyclerView=findViewById(R.id.busRecyclerView);
        busViewModelList=new ArrayList<>();

        busViewModelList.add(new BusViewModel("Uttara",R.drawable.bus));
        busViewModelList.add(new BusViewModel("Mirpur 1",R.drawable.bus));
        busViewModelList.add(new BusViewModel("Biman Bandar",R.drawable.bus));
        busViewModelList.add(new BusViewModel("Badda",R.drawable.bus));
        busViewModelList.add(new BusViewModel("Sonir Akhra",R.drawable.bus));

        BusViewAdapter busViewAdapter=new BusViewAdapter(busViewModelList,this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(busViewAdapter);
    }
}