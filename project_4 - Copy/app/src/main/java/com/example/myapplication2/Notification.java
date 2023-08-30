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

    public Notification() {
        // Required empty public constructor
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

        List<NotificationModel> notificationModels=getData();

        NotificationAdapter notificationAdapter=new NotificationAdapter(notificationModels,getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        nfRecycler.setLayoutManager(linearLayoutManager);
        nfRecycler.setAdapter(notificationAdapter);

        return view;
    }

    private List<NotificationModel> getData(){
        List<NotificationModel> notificationModels=new ArrayList<>();
        notificationModels.add(new NotificationModel("25 july Sunday","6:15 AM","All transportation service are called off\n" +
                "due to Eid ul Adha"));
        notificationModels.add(new NotificationModel("25 july Sunday","6:15 AM","All transportation service are called off\n" +
                "due to Eid ul Adha"));
        notificationModels.add(new NotificationModel("25 july Sunday","6:15 AM","All transportation service are called off\n" +
                "due to Eid ul Adha"));
        notificationModels.add(new NotificationModel("25 july Sunday","6:15 AM","All transportation service are called off\n" +
                "due to Eid ul Adha"));
        notificationModels.add(new NotificationModel("25 july Sunday","6:15 AM","All transportation service are called off\n" +
                "due to Eid ul Adha"));
        return notificationModels;
    }
}