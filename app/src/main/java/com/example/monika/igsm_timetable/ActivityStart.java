package com.example.monika.igsm_timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class ActivityStart extends AppCompatActivity{


    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setupUIViews();
        initToolbar();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        break;
                    case R.id.navi_mustsee:
                        Intent intent_map = new Intent(ActivityStart.this, ActivityMaps.class);
                        startActivity(intent_map);
                        break;
                    case R.id.navi_timetable:
                        Intent intent_timetable = new Intent(ActivityStart.this, MainActivity.class);
                        startActivity(intent_timetable);
                        break;
                }
                return false;
            }
        });
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarStart);

    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("IGSM");
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
