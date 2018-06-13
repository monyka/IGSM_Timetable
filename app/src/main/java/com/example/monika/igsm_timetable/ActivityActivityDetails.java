package com.example.monika.igsm_timetable;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityActivityDetails extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    private ListView listOfActivitiesDetails;

    DayActivityData dayActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);

        Intent intent = getIntent();
        dayActivity = (DayActivityData) intent.getSerializableExtra("activityObject");

        setupUIViews();
        initToolbar();
        setupCardView();

        //TextView activityPlaceDetails = findViewById(R.id.tvActivityDayDetails);
        //activityPlaceDetails.setText(placeDetails);

        //Dolny pasek nawigacyjny
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        Intent intent_start = new Intent(ActivityActivityDetails.this, ActivityStart.class);
                        startActivity(intent_start);
                        break;
                    case R.id.navi_mustsee:
                        Intent intent_map = new Intent(ActivityActivityDetails.this, ActivityMaps.class);
                        startActivity(intent_map);
                    case R.id.navi_timetable:
                        Intent intent_timetable = new Intent(ActivityActivityDetails.this, MainActivity.class);
                        startActivity(intent_timetable);
                        break;
                }
                return false;
            }
        });
    }

    private void setupUIViews() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolbarActivityDetail);
        }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupCardView(){

//        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);
//        String selected_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null);
//        String selected_details_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT_DET, null);
//
//        DatabaseReference ActivitiesDetailsData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day).child(selected_activity);
//        DatabaseReference DayActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day);


        final TextView activityPlaceDetails = findViewById(R.id.tvActivityPlaceDetails);
        final TextView activityPlaceAddress = findViewById(R.id.tvAdressDetails);
        final TextView activityDetails = findViewById(R.id.tvActivityDetails);

        activityPlaceDetails.setText(dayActivity.placeDetails);
        activityPlaceAddress.setText(dayActivity.address);
        activityDetails.setText(dayActivity.activityDetails);
    }

    //powrot do poprzedniego ekarnu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}