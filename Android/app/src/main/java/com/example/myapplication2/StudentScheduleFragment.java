package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication2.Model.ModelPickupPoint;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.PickupPointService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentScheduleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private List<ModelPickupPoint> list;

    private int routeId;

    public StudentScheduleFragment() {
        // Required empty public constructor
    }

    public StudentScheduleFragment(int routeId) {
        // Required empty public constructor
        this.routeId=routeId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentScheduleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentScheduleFragment newInstance(String param1, String param2) {
        StudentScheduleFragment fragment = new StudentScheduleFragment();
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
        View view= inflater.inflate(R.layout.fragment_student_schedule, container, false);

        recyclerView=view.findViewById(R.id.student_pickup_point_recycler_view);

        list=new ArrayList<>();

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        PickupPointService pickupPointService =retrofit.create(PickupPointService.class);

        // Toast.makeText(this,routeId+"",Toast.LENGTH_SHORT).show();

        Call<List<ModelPickupPoint>> call=pickupPointService.getAllPickupPoint(routeId);

        call.enqueue(new Callback<List<ModelPickupPoint>>() {
            @Override
            public void onResponse(Call<List<ModelPickupPoint>> call, Response<List<ModelPickupPoint>> response) {
                if(response.isSuccessful()){
                    //list.addAll(response.body());
                    //Toast.makeText(getApplicationContext(),list.get(0).getPickupPointName(),Toast.LENGTH_SHORT).show();
                    foo(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<ModelPickupPoint>> call, Throwable t) {

            }
        });

        return view;
    }

    void foo(List<ModelPickupPoint> list){
        StudentPickupPointAdapter pickupPointAdapter=new StudentPickupPointAdapter(list,getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(pickupPointAdapter);
    }
}