package com.example.myapplication2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication2.Model.Location;
import com.example.myapplication2.Model.ModelBus;
import com.example.myapplication2.Network.RetrofitInstance;
import com.example.myapplication2.Service.BusService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusTrack#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusTrack extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;

    private Spinner selectBusSpn;
    private List<ModelBus> modelBusList=new ArrayList<>();
    private List<String> busList=new ArrayList<>();
    private ArrayAdapter busAdapter;
    private Button busTrackBtn;
    private String busNo="dhaka-1";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference rootReference;
    DatabaseReference locationReference;
    DatabaseReference busReference;
    DatabaseReference locationReference2;
    DatabaseReference busReference2;
    public BusTrack() {
        // Required empty public constructor
    }

    public BusTrack(String busNo){
        this.busNo=busNo;
        Toast.makeText(getContext(),busNo,Toast.LENGTH_SHORT).show();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusTrack.
     */
    // TODO: Rename and change types and number of parameters
    public static BusTrack newInstance(String param1, String param2) {
        BusTrack fragment = new BusTrack();
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
        View view=inflater.inflate(R.layout.fragment_bus_track, container, false);
        Context ctx = getContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        selectBusSpn=view.findViewById(R.id.select_bus_spn);
        busTrackBtn=view.findViewById(R.id.bus_track_btn);

        selectBusSpn.setOnItemSelectedListener(this);

        setBusSpinner();

        busTrackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.rel1, new BusTrack(busNo)).commit();
            }
        });

        map = (MapView) view.findViewById(R.id.bus_track_map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        requestPermissionsIfNecessary(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        IMapController mapController = map.getController();
        mapController.setZoom(16.7);

        GeoPoint startPoint = new GeoPoint(23.72578763160967d, 90.39059429730275d);
        mapController.setCenter(startPoint);
//
//        GeoPoint gPt0 = new GeoPoint(23.72578763160967d, 90.39059429730275d);
//        GeoPoint gPt1 = new GeoPoint(23.74578763160967d, 90.39259429730275d);
//        ArrayList<GeoPoint> line=new ArrayList<>();
//        line .add(gPt0);
//        line .add(gPt1);
//
//        String userAgent = BuildConfig.APPLICATION_ID+"/"+BuildConfig.VERSION_NAME;
//
//        RoadManager roadManager = new OSRMRoadManager(getContext(), "OBP_Tuto/1.0");
//
//        Road road = roadManager.getRoad(line);
//        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);
//
//        map.getOverlays().add(roadOverlay);


        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        startMarker.setIcon(getResources().getDrawable(R.drawable.location1));
        startMarker.setTitle("Start point");

        map.getOverlays().add(startMarker);

        map.invalidate();

//        if(locationReference!=null){
//            Toast.makeText(getContext(),"start foo",Toast.LENGTH_SHORT).show();
//            foo();
//        }

//        firebaseDatabase=FirebaseDatabase.getInstance();
//        rootReference=firebaseDatabase.getReference();
//        busReference=rootReference.child("dhaka-1");
//        locationReference=busReference.child("location");

        //foo();

        firebaseDatabase=FirebaseDatabase.getInstance();
        rootReference=firebaseDatabase.getReference();
        busReference=rootReference.child("dhaka-8");
        locationReference=busReference.child("location");

        foo();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }


    private void foo(){
        locationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<GeoPoint> geoPointList=new ArrayList<>();
                List<Location> locationList=new ArrayList<>();
                int i=1;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    // locationList.add(location);
                    //geoPointList.add(new GeoPoint(location.getLatitude(),location.getLongitude()));

                    Location location=dataSnapshot.getValue(Location.class);

                    geoPointList.add(new GeoPoint(location.getLatitude(),location.getLongitude()));


                    //Location2 location2=dataSnapshot.getValue(Location2.class);
                    //String latitude= String.valueOf(dataSnapshot.getValue(Location.class).getLatitude());
                    //Toast.makeText(getContext(),location.getLatitude()+"",Toast.LENGTH_SHORT).show();
                    i++;
                }

                map.getOverlays().clear();

                drawInMap2(geoPointList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void drawInMap(List<GeoPoint> geoPointList){
        IMapController mapController = map.getController();
        mapController.setZoom(16.7);

        mapController.setCenter(geoPointList.get(0));


        RoadManager roadManager = new OSRMRoadManager(getContext(), "OBP_Tuto/1.0");

        Road road = roadManager.getRoad((ArrayList<GeoPoint>) geoPointList);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);

        roadOverlay.setWidth(10.5F);
        roadOverlay.setColor(R.color.buet);

        map.getOverlays().add(roadOverlay);

        map.invalidate();
    }

    private void drawInMap2(List<GeoPoint> geoPointList){
        if(geoPointList.size()==0){
            return;
        }
        GeoPoint startPoint=geoPointList.get(geoPointList.size()-1);
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        startMarker.setIcon(getResources().getDrawable(R.drawable.location1));
        startMarker.setTitle("Start point");
        startMarker.setTextLabelFontSize(100);

        map.getOverlays().add(startMarker);

        map.invalidate();

    }


    public void setBusSpinner(){
        Retrofit retrofit= RetrofitInstance.getRetrofitInstance();
        BusService busService=retrofit.create(BusService.class);

        Call<List<ModelBus>> call= busService.getAllBus();

        call.enqueue(new Callback<List<ModelBus>>() {
            @Override
            public void onResponse(Call<List<ModelBus>> call, Response<List<ModelBus>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"successfully",Toast.LENGTH_SHORT).show();
                    setBusList(response.body());
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
    }

    public void setBusList(List<ModelBus> modelBusList){
        this.modelBusList.addAll(modelBusList);
        for(ModelBus modelBus:modelBusList){
            this.busList.add(modelBus.getBusNumber());
            Toast.makeText(getContext(),modelBus.getBusNumber(),Toast.LENGTH_SHORT);
        }
        busAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, this.busList);
        busAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectBusSpn.setAdapter(busAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        this.busNo= busList.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}