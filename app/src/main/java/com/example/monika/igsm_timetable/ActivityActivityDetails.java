package com.example.monika.igsm_timetable;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
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
    private CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);


        setupUIViews();
        initToolbar();
        setupCardView();



        //String dayName = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);
        String placeDetails = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT_DET, null);

        //TextView activityPlaceDetails = findViewById(R.id.tvActivityDayDetails);
        //activityPlaceDetails.setText(placeDetails);
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

        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);
        String selected_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null);
        String selected_details_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT_DET, null);

        DatabaseReference ActivitiesDetailsData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day).child(selected_activity);
        DatabaseReference DayActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day);


        final TextView activityPlaceDetails = findViewById(R.id.tvActivityDayDetails);
        final TextView activityName = findViewById(R.id.activityName);

        //activityPlaceDetails.setText(selected_details_activity);
        activityName.setText(selected_activity);

//        System.out.println(selected_activity);
//        System.out.println(selected_activity);
//        System.out.println(selected_activity);
//        System.out.println(selected_activity);
        //TextView name = findViewById(R.id.activityName);
        //name.setText(selected_activity);


        ActivitiesDetailsData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                System.out.println(dataSnapshot.getKey());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DayActivitiesData.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //String value = dataSnapshot.getValue(String.class);
                //System.out.println(value);
                Iterable<DataSnapshot> activities = dataSnapshot.getChildren();

                ///System.out.println(dataSnapshot.getKey());
                ///System.out.println(activities);

                if (!dataSnapshot.getKey().equals("id")) {

                    //musza być wszystkie elementy jak w strukturze bazy
                    String activityDetails = (String) activities.iterator().next().getKey();
                    String address = (String) activities.iterator().next().getValue();
                    String hours = (String) activities.iterator().next().getValue();
                    String id = (String) activities.iterator().next().getValue();
                    String place = (String) activities.iterator().next().getValue();
                    String placeDetails = (String) activities.iterator().next().getValue();

                    String key = dataSnapshot.getKey();

//                  activityPlaceDetails.add(placeDetails);
//
//                    DayActivitiesArray.add(new DayActivity(key, hours, place));
//                    listOfActivitiesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //pobieranie aktywnosci, czasu i miejsca
//    private void setupListView() {
//
//        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);
//        String selected_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null);
//        String selected_activity_details = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT_DET, null);
//
//        //DatabaseReference DayActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day);
//        DatabaseReference ActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day).child(selected_activity);
//        DatabaseReference ActivitiesDetailsData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day).child(selected_activity).child(selected_activity_details);
//
//        //lista
//        cardView = (CardView) findViewById(R.id.detailsCardView);
//
//        //adapter
////        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsername);
//        final ActivitiesAdapter listOfActivitiesAdapter = new ActivitiesAdapter(this, DayActivitiesArray);
//
//        //ustawienie listy do adaptera
//        ListOfActivities.setAdapter(listOfActivitiesAdapter);
//
//        ListOfActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                startActivity(new Intent(ActivityDay.this, ActivityActivityDetails.class));
//                TextView actName = view.findViewById(R.id.tvActivityDayDetails);
//                MainActivity.sharedPreferences.edit().putString(MainActivity.SEL_ACT, actName.getText().toString()).apply();
//            }
//        });
//
//
//    }
}