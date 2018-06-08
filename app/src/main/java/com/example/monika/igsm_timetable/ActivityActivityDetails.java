package com.example.monika.igsm_timetable;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ActivityActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_details);

        String activityName = MainActivity.sharedPreferences.getString(MainActivity.SEL_ACT, null);
        String dayName = MainActivity.sharedPreferences.getString(MainActivity.SEL_DAY, null);

        TextView name = findViewById(R.id.activityName);
        name.setText(activityName);
    }
}
