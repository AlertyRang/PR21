package com.example.app21;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit.*;
import com.yandex.mapkit.*;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.layers.GeoObjectTapEvent;
import com.yandex.mapkit.layers.GeoObjectTapListener;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Cluster;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.mapview.MapView;

import java.sql.DriverPropertyInfo;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapview;
    private MapKit mapkit;

    private  List<Point> lst1;
    private  List<Point> Pint= new ArrayList<>();
    private Point TARGET_LOCATION = new Point(59.945933, 30.320045);
    private Point TARGET_LOCATION1 = new Point(55.751574, 37.573856);
    private Point TARGET_LOCATION2 = new Point(50.751574, 35.573856);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapKitFactory.setApiKey("30309b9a-b697-485d-a97a-a58a9af81d8d");
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        mapkit = MapKitFactory.getInstance();
        mapview = findViewById(R.id.mapview);
        mapview.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null);
        mapview.getMap().getMapObjects().addPlacemark(TARGET_LOCATION);
        mapview.getMap().getMapObjects().addPlacemark(TARGET_LOCATION1);

        lst1 = new ArrayList<>();
        mapview.getMap().addTapListener(new GeoObjectTapListener() {
            @Override
            public boolean onObjectTap(@NonNull GeoObjectTapEvent geoObjectTapEvent) {
                Log.d("Main",geoObjectTapEvent.getGeoObject().getName());
                return false;
            }
        });
    }
    @Override
    protected void onStop() {
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapview.onStart();
    }
}