package com.example.myapplication2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelTicket;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.TicketService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuyTicket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuyTicket extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String userName;

    private EditText numberOfTicketEt;
    private Button buyTicketConfirmBtn;
    private Button buyTicketCancelBtn;
    private int amount;

    public BuyTicket() {
        // Required empty public constructor
    }

    public BuyTicket(String userName,int amount){
        this.userName=userName;
        this.amount=amount;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuyTicket.
     */
    // TODO: Rename and change types and number of parameters
    public static BuyTicket newInstance(String param1, String param2) {
        BuyTicket fragment = new BuyTicket();
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
        View view= inflater.inflate(R.layout.fragment_buy_ticket, container, false);
        numberOfTicketEt=view.findViewById(R.id.number_of_tickets_et);
        buyTicketConfirmBtn=view.findViewById(R.id.buy_ticket_confirm_btn);
        buyTicketCancelBtn=view.findViewById(R.id.buy_ticket_cancel_btn);

        buyTicketConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfTickets=Integer.parseInt(numberOfTicketEt.getText().toString())+amount;
                ModelTicket modelTicket=new ModelTicket(userName,numberOfTickets,true);
                Retrofit retrofit=RetrofitInstance.getRetrofitInstance();
                TicketService ticketService=retrofit.create(TicketService.class);
                Call<ModelTicket> call=ticketService.editTicket(modelTicket,userName);
                call.enqueue(new Callback<ModelTicket>() {
                    @Override
                    public void onResponse(Call<ModelTicket> call, Response<ModelTicket> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"ticket created successfully",Toast.LENGTH_SHORT);
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelTicket> call, Throwable t) {

                    }
                });
            }
        });

        buyTicketCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}