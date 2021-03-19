package com.example.redmoonstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView add = findViewById(R.id.add);
        CardView Event = findViewById(R.id.Event);
        CardView photographer = findViewById(R.id.photographer);
        CardView viewEvent = findViewById(R.id.viewEvent);
        CardView Report = findViewById(R.id.Report);
        CardView logout = findViewById(R.id.logout);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent in = new Intent(Dashboard.this, Photographer.class);
                startActivity(in);
            }
        });
        Event.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent event = new Intent(Dashboard.this, Add_Event.class);
                startActivity(event);
            }
        });
        photographer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent photo = new Intent(Dashboard.this, viewPhotographer.class);
                startActivity(photo);
            }
        });
        viewEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent Eventview = new Intent(Dashboard.this, Events.class);
                startActivity(Eventview);
            }
        });
        Report.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent Report = new Intent(Dashboard.this, Report.class);
                startActivity(Report);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                SharedPreferences sharedPre = getSharedPreferences("login_info", MODE_PRIVATE);
                sharedPre.edit().putBoolean("logged_in", false).commit();

                Intent activityChangeIntent = new Intent(Dashboard.this, MainActivity.class);
                startActivity(activityChangeIntent);

            }
        });
    }
}