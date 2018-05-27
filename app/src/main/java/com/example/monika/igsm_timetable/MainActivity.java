package com.example.monika.igsm_timetable;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button button_monday;
    public Button button_start;


    public void init() {
        button_monday=(Button)findViewById(R.id.button_monday);
        button_monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent naviday = new Intent(MainActivity.this,ActivityDay.class);
                startActivity(naviday);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        Intent intent_start = new Intent(MainActivity.this, ActivityStart.class);
                        startActivity(intent_start);
                        break;
                    case R.id.navi_mustsee:
                        break;
                    case R.id.navi_timetable:
                        break;
                }
                return false;
            }
        });
    }
}
