package com.example.myapplication2;

import static android.content.Context.WINDOW_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication2.Model.ModelStudent;
import com.example.myapplication2.Model.ModelTicket;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.StudentService;
import com.example.myapplication2.Service.TicketService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentTicket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentTicket extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String userName;
    private TextView numberOfTicketsTextView;
    private Button buyMoreBtn;
    private ImageView ticketQRIv;
    private String ticketId;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    private int amount;


    public StudentTicket() {
        // Required empty public constructor
    }

    public StudentTicket(String userName){
        this.userName=userName;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentTicket.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentTicket newInstance(String param1, String param2) {
        StudentTicket fragment = new StudentTicket();
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
        View view=inflater.inflate(R.layout.fragment_student_ticket, container, false);
        numberOfTicketsTextView=view.findViewById(R.id.number_of_tickets_tv);
        buyMoreBtn=view.findViewById(R.id.buy_more_btn);
        ticketQRIv=view.findViewById(R.id.ticket_qr_iv);

        //generateQRCode2(null);

        retrieveTicketId();

        //showTicketNumber("abcd");

        buyMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.rel1, new BuyTicket(ticketId,amount)).commit();

            }
        });

        return view;
    }

    public void retrieveTicketId(){
        Retrofit retrofit=RetrofitInstance.getRetrofitInstance();
        StudentService studentService=retrofit.create(StudentService.class);

        Call<ModelStudent> call=studentService.getStudent(userName);

        call.enqueue(new Callback<ModelStudent>() {
            @Override
            public void onResponse(Call<ModelStudent> call, Response<ModelStudent> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "successfully", Toast.LENGTH_SHORT).show();
                    String ticketId=response.body().getTicketId();
                    setTicketId(ticketId);
                    showTicketNumber(ticketId);
                    generateQRCode2(ticketId);
                }
            }

            @Override
            public void onFailure(Call<ModelStudent> call, Throwable t) {

            }
        });
    }

    public void showTicketNumber(String ticketId){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        TicketService ticketService=retrofit.create(TicketService.class);

        Call<ModelTicket> call=ticketService.getTicket(ticketId);
        call.enqueue(new Callback<ModelTicket>() {
            @Override
            public void onResponse(Call<ModelTicket> call, Response<ModelTicket> response) {
                if(response.isSuccessful()){
                    ModelTicket modelTicket=response.body();
                    setAmount(modelTicket.getNumberOfTickets());
                    numberOfTicketsTextView.setText(modelTicket.getNumberOfTickets()+"");
                }
            }

            @Override
            public void onFailure(Call<ModelTicket> call, Throwable t) {

            }
        });
    }



    public void generateQRCode2(String ticketId){
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(ticketId, BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap1=barcodeEncoder.createBitmap(bitMatrix);
            ticketQRIv.setImageBitmap(bitmap1);
        }catch (WriterException e){
            throw new RuntimeException(e);
        }
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}