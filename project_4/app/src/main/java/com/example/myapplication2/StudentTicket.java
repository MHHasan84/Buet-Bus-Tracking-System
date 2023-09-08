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

import com.example.myapplication2.Model.ModelTicket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

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
    private Button button;
    private ImageView ticketQRIv;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

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
        button=view.findViewById(R.id.buy_more_btn);
        ticketQRIv=view.findViewById(R.id.ticket_qr_iv);

        generateQRCode2(null);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.rel1, new BuyTicket()).commit();

            }
        });

        return view;
    }

    public void generateQRCode(ModelTicket ticket){
        WindowManager manager= (WindowManager) getContext().getSystemService(WINDOW_SERVICE);

        // initializing a variable for default display.
        Display display = manager.getDefaultDisplay();

        // creating a variable for point which
        // is to be displayed in QR Code.
        Point point = new Point();
        display.getSize(point);

        // getting width and
        // height of a point
        int width = point.x;
        int height = point.y;

        // generating dimension from width and height.
        int dimen = width < height ? width : height;
        dimen = dimen * 3 / 4;

        // setting this dimensions inside our qr code
        // encoder to generate our qr code.
        qrgEncoder = new QRGEncoder("hasan", null, QRGContents.Type.TEXT, dimen);
        // getting our qrcode in the form of bitmap.
        bitmap = qrgEncoder.getBitmap();
        // the bitmap is set inside our image
        // view using .setimagebitmap method.
        ticketQRIv.setImageBitmap(bitmap);
    }


    public void generateQRCode2(ModelTicket ticket){
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode("Mahmudul hasan", BarcodeFormat.QR_CODE,300,300);
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap1=barcodeEncoder.createBitmap(bitMatrix);
            ticketQRIv.setImageBitmap(bitmap1);
        }catch (WriterException e){
            throw new RuntimeException(e);
        }
    }
}