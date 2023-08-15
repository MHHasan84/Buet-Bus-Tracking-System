package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHome#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView nearbyBus;
    private RecyclerView schedule;
    private List<NearbyBus> nearbyBusList;
    private List<ScheduleModel> scheduleModelList;


    public StudentHome() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentHome.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHome newInstance(String param1, String param2) {
        StudentHome fragment = new StudentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_student_home, container, false);

        nearbyBus=view.findViewById(R.id.nearby_bus);
        schedule=view.findViewById(R.id.schedule_recycler);

        nearbyBusList=new ArrayList<>();
        scheduleModelList=getData();

        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));
        nearbyBusList.add(new NearbyBus(1,"06:15 AM","Uttora"));

        NearbyBusAdapter nearbyBusAdapter=new NearbyBusAdapter(nearbyBusList,getContext());
        LinearLayoutManager nearbyBusLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        nearbyBus.setLayoutManager(nearbyBusLayoutManager);
        nearbyBus.setAdapter(nearbyBusAdapter);

        ScheduleAdapter scheduleAdapter=new ScheduleAdapter(scheduleModelList,getContext());
        LinearLayoutManager scheduleLayoutManager=new LinearLayoutManager(getContext());

        schedule.setLayoutManager(scheduleLayoutManager);
        schedule.setAdapter(scheduleAdapter);

        return view;
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