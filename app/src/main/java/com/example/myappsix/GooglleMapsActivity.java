package com.example.myappsix;

import static androidx.core.location.LocationManagerCompat.getCurrentLocation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.Manifest;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class GooglleMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap gMap;
    SupportMapFragment mapView;
    Location mLastLocation;
    Marker mCurrentLocationMarker;
    FusedLocationProviderClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlle_maps);

        mapView = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapView.getMapAsync(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        
        getCurrentLocation();
    }

    private void getCurrentLocation() {

        if(ActivityCompat.checkSelfPermission(GooglleMapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){

            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if(location != null){
                        mapView.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {


                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                MarkerOptions options = new MarkerOptions().position(latLng)
                                        .title("I' m here...");
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,50));
                                googleMap.addMarker(options);
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
                    Log.d("SMS Permission", "Request permission to send SMS...");
                    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                    requestPermissions(permissions, 100);

                }
            }
            getCurrentLocation();
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


    }
}