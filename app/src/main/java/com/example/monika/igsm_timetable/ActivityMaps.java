package com.example.monika.igsm_timetable;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;




public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    ChildEventListener mChildEventListener;
    DatabaseReference MarkersRef = FirebaseDatabase.getInstance().getReference("Markers");


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
         //Add a marker in Sydney and move the camera
        LatLng pw = new LatLng(52.221485, 21.008104);
        mMap.addMarker(new MarkerOptions().position(pw).title("Politechnika Warszawska")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pw));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pw, 13));




    }
}
