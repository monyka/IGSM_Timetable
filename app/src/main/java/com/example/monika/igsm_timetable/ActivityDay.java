package com.example.monika.igsm_timetable;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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

import java.io.Serializable;
import java.util.ArrayList;


public class ActivityDay extends AppCompatActivity implements ValueEventListener {

    private ListView listOfActivities;
    private ArrayList<DayActivityData> dayActivitiesArray = new ArrayList<>();

    private android.support.v7.widget.Toolbar toolbar;


    //adapter dla danych Day
    public class ActivitiesAdapter extends ArrayAdapter<DayActivityData> {
        public ActivitiesAdapter(Context context, ArrayList<DayActivityData> users) {
            super(context, 0, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            DayActivityData user = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_day_detail_single_item, parent, false);
            }
            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.tvActivityDayDetails);
            TextView tvHome = (TextView) convertView.findViewById(R.id.tvTimeDayDetail);
            TextView tvPlace = (TextView) convertView.findViewById(R.id.tvPlace);


            // Populate the data into the template view using the data object
            tvName.setText(user.activity_name);
            tvHome.setText(user.hours);
            tvPlace.setText(user.place);
            // Return the completed view to render on screen
            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        initToolbar();
        setupListView();

        //Dolny pasek nawigacyjny
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        Intent intent_start = new Intent(ActivityDay.this, ActivityStart.class);
                        startActivity(intent_start);
                        break;
                    case R.id.navi_mustsee:
                        Intent intent_map = new Intent(ActivityDay.this, ActivityMaps.class);
                        startActivity(intent_map);
                    case R.id.navi_timetable:
                        Intent intent_timetable = new Intent(ActivityDay.this, MainActivity.class);
                        startActivity(intent_timetable);
                        break;
                }
                return false;
            }
        });
    }

    private void setupUIViews(){
        listOfActivities = (ListView)findViewById(R.id.lvDayDetail);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolbarActivityDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //pobieranie aktywnosci, czasu i miejsca
    private void setupListView(){

        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);
        String selected_activity = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null);

        DatabaseReference DayActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day);
        //DatabaseReference ActivitiesData = FirebaseDatabase.getInstance().getReference("Days").child(selected_day).child(selected_activity);

        //sortowanie po id
        final Query DayActivityDataQuery = DayActivitiesData.orderByChild("id");

        //lista
        listOfActivities = (ListView)findViewById(R.id.lvDayDetail);

        //adapter
        final ActivitiesAdapter listOfActivitiesAdapter = new ActivitiesAdapter(this, dayActivitiesArray);

        //ustawienie listy do adaptera
        listOfActivities.setAdapter(listOfActivitiesAdapter);

        listOfActivities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ActivityDay.this, ActivityActivityDetails.class);
                intent.putExtra("activityObject", dayActivitiesArray.get(position));
                startActivity(intent);
                TextView actName = view.findViewById(R.id.tvActivityDayDetails);
                MainActivity.sharedPreferences.edit().putString(MainActivity.SEL_ACT, actName.getText().toString()).apply();
            }
        });

        //pobieranie danych z bazy do listy
        DayActivityDataQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //String value = dataSnapshot.getValue(String.class);
                Iterable<DataSnapshot> activities = dataSnapshot.getChildren();

//                System.out.println(dataSnapshot.getKey());
//                System.out.println(activities);

                if(!dataSnapshot.getKey().equals("id")) {

                    String activityDetails = (String) activities.iterator().next().getValue();
                    String address = (String) activities.iterator().next().getValue();
                    String hours = (String) activities.iterator().next().getValue();
                    String id = (String) activities.iterator().next().getValue();
                    String lat = (String) activities.iterator().next().getValue();
                    String lng = (String) activities.iterator().next().getValue();
                    String place = (String) activities.iterator().next().getValue();
                    String placeDetails = (String) activities.iterator().next().getValue();

                    String key = dataSnapshot.getKey();

                    dayActivitiesArray.add(new DayActivityData(key, hours, place, activityDetails, address, placeDetails, lat, lng));
                    listOfActivitiesAdapter.notifyDataSetChanged();
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

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {


    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

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
