package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentBusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentBusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;

    public StudentBusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentBusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentBusFragment newInstance(String param1, String param2) {
        StudentBusFragment fragment = new StudentBusFragment();
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
        View view= inflater.inflate(R.layout.fragment_student_bus, container, false);

        recyclerView=view.findViewById(R.id.std_busRecyclerView);

        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        List<ModelBus> modelBusList=new ArrayList<>();
        Call<List<ModelBus>> call= busService.getAllBus();
        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    modelBusList.addAll(response.body());

                    foo(modelBusList);

                }
                else{
                    Toast.makeText(getContext(),"not successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelBus>> call, Throwable t) {
                Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    void foo(List<ModelBus> modelBusList){
        StudentBusViewAdapter busViewAdapter=new StudentBusViewAdapter(modelBusList,getContext(),getFragmentManager());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(busViewAdapter);
    }
}