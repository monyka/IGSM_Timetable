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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.monika.igsm_timetable.Utils.LetterImageView;

//import com.example.monika.igsm_timetable.Utils.LetterImageView;

public class DayDetail extends AppCompatActivity {

    private ListView listView;
    private android.support.v7.widget.Toolbar toolbar;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Time1;
    public static String[] Time2;
    public static String[] Time3;
    public static String[] Time4;
    public static String[] Time5;
    public static String[] Time6;
    public static String[] Place1;
    public static String[] Place2;
    public static String[] Place3;
    public static String[] Place4;
    public static String[] Place5;
    public static String[] Place6;

    private String[] PreferredDay;
    private String[] PreferredTime;
    private String[] PreferredPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        setupUIViews();
        initToolbar();
        setupListView();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        Intent intent_start = new Intent(DayDetail.this, ActivityStart.class);
                        startActivity(intent_start);
                        break;
                    case R.id.navi_mustsee:
                        Intent intent_map = new Intent(DayDetail.this, MapsActivity.class);
                        startActivity(intent_map);
                    case R.id.navi_timetable:
                        Intent intent_timetable = new Intent(DayDetail.this, MainActivity.class);
                        startActivity(intent_timetable);
                        break;
                }
                return false;
            }
        });
    }


    private void setupUIViews(){
        listView = (ListView)findViewById(R.id.lvDayDetail);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ToolbarDayDetail);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //pobieranie aktywnosci, czasu i miejsca
    private void setupListView(){

        Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);

        Time1 = getResources().getStringArray(R.array.time1);
        Time2 = getResources().getStringArray(R.array.time2);
        Time3 = getResources().getStringArray(R.array.time3);
        Time4 = getResources().getStringArray(R.array.time4);
        Time5 = getResources().getStringArray(R.array.time5);
        Time6 = getResources().getStringArray(R.array.time6);

        Place1 = getResources().getStringArray(R.array.place1);
        Place2 = getResources().getStringArray(R.array.place2);
        Place3 = getResources().getStringArray(R.array.place3);
        Place4 = getResources().getStringArray(R.array.place4);
        Place5 = getResources().getStringArray(R.array.place5);
        Place6 = getResources().getStringArray(R.array.place6);

        String selected_day = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);

        if(selected_day.equalsIgnoreCase("Monday")){
            PreferredDay = Monday;
            PreferredTime = Time1;
            PreferredPlace = Place1;
        }else if(selected_day.equalsIgnoreCase("Tuesday")){
            PreferredDay = Tuesday;
            PreferredTime = Time2;
            PreferredPlace = Place2;
        }else if(selected_day.equalsIgnoreCase("Wednesday")){
            PreferredDay = Wednesday;
            PreferredTime = Time3;
            PreferredPlace = Place3;
        }else if(selected_day.equalsIgnoreCase("Thursday")){
            PreferredDay = Thursday;
            PreferredTime = Time4;
            PreferredPlace = Place4;
        }else if(selected_day.equalsIgnoreCase("Friday")){
            PreferredDay = Friday;
            PreferredTime = Time5;
            PreferredPlace = Place5;
        }else{
            PreferredDay = Saturday;
            PreferredTime = Time6;
            PreferredPlace = Place6;
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(DayDetail.this, PreferredDay, PreferredTime, PreferredPlace);
        listView.setAdapter(simpleAdapter);

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView activity, time, place;
        private String[] ActivityArray;
        private String[] timeArray;
        private String[] placeArray;
        private LetterImageView letterImageView;


        public SimpleAdapter(Context context, String[] activityArray, String[] timeArray, String[] placeArray){
            mContext = context;
            this.ActivityArray = activityArray;
            this.timeArray = timeArray;
            this.placeArray = placeArray;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return ActivityArray.length;
        }

        @Override
        public Object getItem(int position) {
            return ActivityArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.day_detail_single_item, null);
            }

            activity = (TextView)convertView.findViewById(R.id.tvActivityDayDetails);
            time = (TextView)convertView.findViewById(R.id.tvTimeDayDetail);
            place = (TextView)convertView.findViewById(R.id.tvPlace);

            activity.setText(ActivityArray[position]);
            time.setText(timeArray[position]);
            place.setText(placeArray[position]);

            return convertView;

        }
    }

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
