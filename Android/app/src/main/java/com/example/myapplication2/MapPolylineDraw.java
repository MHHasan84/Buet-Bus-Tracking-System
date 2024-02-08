package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.os.Bundle;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polyline;

public class MapPolylineDraw extends AppCompatActivity {
    private MapView map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_polyline_draw);

        map=findViewById(R.id.map_polyline);
        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setMultiTouchControls(true);

        IMapController mapController = map.getController();
        mapController.setZoom(6.7);

        GeoPoint startPoint = new GeoPoint(52.507621d, 13.407334d);
        mapController.setCenter(startPoint);

        GeoPoint gPt0 = new GeoPoint(52.507621d, 13.407334d);
        GeoPoint gPt1 = new GeoPoint(52.527621d, 13.427334d);
        Polyline line = new Polyline(map);
        line .addPoint(gPt0);
        line .addPoint(gPt1);

        map.getOverlays().add(line);
    }
}