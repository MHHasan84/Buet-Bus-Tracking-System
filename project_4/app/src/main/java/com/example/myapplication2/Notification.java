package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelNotification;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.NotificationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Notification#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Notification extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView nfRecycler;

    private String studentId;

    public Notification() {
        // Required empty public constructor
    }

    public Notification(String studentId){
        this.studentId=studentId;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Notification.
     */
    // TODO: Rename and change types and number of parameters
    public static Notification newInstance(String param1, String param2) {
        Notification fragment = new Notification();
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
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        nfRecycler=view.findViewById(R.id.nf_recycler);

        setNotificationList();

        return view;
    }

    public void setNotificationList(){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        NotificationService notificationService=retrofit.create(NotificationService.class);

        Call<List<ModelNotification>> call=notificationService.getNotification(studentId);

        call.enqueue(new Callback<List<ModelNotification>>() {
            @Override
            public void onResponse(Call<List<ModelNotification>> call, Response<List<ModelNotification>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "successfully", Toast.LENGTH_SHORT).show();
                    NotificationAdapter notificationAdapter=new NotificationAdapter(response.body(),getContext());
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

                    nfRecycler.setLayoutManager(linearLayoutManager);
                    nfRecycler.setAdapter(notificationAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ModelNotification>> call, Throwable t) {

            }
        });
    }
}