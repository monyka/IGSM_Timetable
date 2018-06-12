package com.example.monika.igsm_timetable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //klasa trzymająca dni tygodnia, które są w formie listy i bedą uzupełniane danymi z firebase
    public class DayOfWeek {
        public String Day;

    //konstruktor
    public DayOfWeek(String Day) {
            this.Day = Day;
        }
    }

    //deklaracja listy oraz tablicy potrzebnej do wprowadzenia danych z firebase
    private ListView ListOfDays;
    private ArrayList<DayOfWeek> DaysOfWeekArray = new ArrayList<>();

    private Toolbar toolbar;

    //SHARED PREFERENCES
    public static SharedPreferences sharedPreferences;
    public static String SEL_DAY = "sel_day";
    public static final String SEL_ACT = "sel_act";
    public static final String SEL_ACT_DET = "sel_act_det";


    //KLASA adapter z ustalonym stylem listy umieszczajacy obiekty w liscie
    public class DaysAdapter extends ArrayAdapter<DayOfWeek> {
        public DaysAdapter(Context context, ArrayList<DayOfWeek> days) {
            super(context, 0, days);
        }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Get the data item for this position
                DayOfWeek day = getItem(position);

                // Check if an existing view is being reused, otherwise inflate the view
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_single_item, parent, false);
                }

                // Lookup view for data population
                TextView tvDayName = (TextView) convertView.findViewById(R.id.tvDayName);

                // Populate the data into the template view using the data object
                tvDayName.setText(day.Day);

                // Return the completed view to render on screen
                return convertView;
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
        initToolbar();
        setupListView();

        //Dolny pasek nawigacyjny
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_start:
                        Intent intent_start = new Intent(MainActivity.this, ActivityStart.class);
                        startActivity(intent_start);
                        break;
                    case R.id.navi_mustsee:
                        Intent intent_map = new Intent(MainActivity.this, ActivityMaps.class);
                        startActivity(intent_map);
                    case R.id.navi_timetable:
                        break;
                }
                return false;
            }
        });
    }

    //ustalenie wygladu ekranu - toolbaru tytulowego oraz listy z dniami tygodnia
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarMain);
        //ListView = (ListView)findViewById(R.id.lvMain);
        ListOfDays = (ListView)findViewById(R.id.lvMain);
        sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE);
    }

    //zainicjowanie toolbaru tytulowego
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" IGSM Timetable");
    }

    private void setupListView(){

        //Referencja do firebase na root o nazwie "Week"
        //PO ZMIANIE STRUKTURY BAZY NA TAKA ZEBY KAZDY DZIEN TYGODNIA BYL NOWA GALEZIA W BAZIE TRZEBA BEDZIE ZROBIC ZMIANY
        //W KODZIE. TZZ MAJAC REF NA NP.FRIDAY ZEBY ODNIESC SIE DO POSZCZEGOLNYCH ACTIVITIES Z FRIDAY TRZEBA ZROBIC:
        // String key = dataSnapshot.getKey(); ,

        String selected_day = MainActivity.sharedPreferences.getString(SEL_DAY, null);

        DatabaseReference DayOfWeekRef = FirebaseDatabase.getInstance().getReference("Days");

        final Query dayQuery = DayOfWeekRef.orderByChild("id");

        //pobranie view z xml
        ListOfDays = (ListView)findViewById(R.id.lvMain);

        //utworzenie adaptera o typie DaysAdapter
        final DaysAdapter arrayAdapterDays = new DaysAdapter(this, DaysOfWeekArray);

        //ustawienie adaptera w liscie
        ListOfDays.setAdapter(arrayAdapterDays);

        ListOfDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(MainActivity.this, ActivityDay.class));
                TextView DayName = view.findViewById(R.id.tvDayName);
                MainActivity.sharedPreferences.edit().putString(MainActivity.SEL_DAY, DayName.getText().toString()).apply();



//                switch(position){
//                    case 0: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Monday").apply();
//                        break;
//                    }
//                    case 1: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply();
//                        break;
//                    }
//                    case 2: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply();
//                        break;
//                    }
//                    case 3: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply();
//                        break;
//                    }
//                    case 4: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Friday").apply();
//                        break;
//                    }
//                    case 5: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply();
//                        break;
//                    }
//                    case 6: {
//                        startActivity(new Intent(MainActivity.this, ActivityDay.class));
//                        sharedPreferences.edit().putString(SEL_DAY, "Sunday").apply();
//                        break;
//                    }
//                    default:break;
//                }
            }
        });

        //pobieranie danych z bazy do listy
        dayQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //String value = dataSnapshot.getValue(String.class);
                //Iterable<DataSnapshot> activities = dataSnapshot.getChildren();

                //System.out.println(dataSnapshot.getKey());

                //String day = dataSnapshot.getValue(String.class);
                String day2 = dataSnapshot.getKey();


                //String value = (String)activities.iterator().next().getValue();
                // value2 = (String)activities.iterator().next().getValue();
                //String key = dataSnapshot.getKey();

                DaysOfWeekArray.add(new DayOfWeek(day2));

                //Notifies the attached observers that the underlying data has been changed
                // and any View reflecting the data set should refresh itself.
                arrayAdapterDays.notifyDataSetChanged();
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
}
