package com.example.monika.igsm_timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class ActivityDay extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day);


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
                        break;
                    case R.id.navi_timetable:
                        Intent intent_timetable = new Intent(ActivityDay.this, MainActivity.class);
                        startActivity(intent_timetable);
                        break;
                }
                return false;
            }
        });
    }
}