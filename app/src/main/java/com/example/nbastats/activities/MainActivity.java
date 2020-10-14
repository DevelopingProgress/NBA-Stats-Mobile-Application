package com.example.nbastats.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nbastats.R;

public class MainActivity extends AppCompatActivity {

    Button matchesButton, standingsButton, playoffsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        matchesButton = (Button) findViewById(R.id.matches_button);
        matchesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MatchesActivity.class);
                startActivity(intent);
            }
        });
        standingsButton = (Button) findViewById(R.id.standings_button);
        standingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StandingsActivity.class);
                startActivity(intent);
            }
        });
        playoffsButton = (Button) findViewById(R.id.playoffs_button);
        playoffsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayoffsActivity.class);
                startActivity(intent);
            }
        });


    }

}