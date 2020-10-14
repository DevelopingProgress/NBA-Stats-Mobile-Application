package com.example.nbastats.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nbastats.R;
import com.example.nbastats.adapters.PlayoffsAdapter;
import com.example.nbastats.adapters.TeamsAdapter;
import com.example.nbastats.models.PlayersModel;
import com.example.nbastats.models.PlayoffModel;
import com.example.nbastats.models.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayoffsActivity extends AppCompatActivity {

    private String url = "https://data.nba.net/prod/v1/2019/playoffsBracket.json";

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private List<PlayoffModel> modelList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Playoffs");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelList = new ArrayList<>();
        loadUrlData();

    }
    private void loadUrlData() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try{
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("series");

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject o = jsonArray.getJSONObject(i);
                        String round, team1Name, team2Name, wins1, wins2,confName, summaryStatusText;
                        round = o.getString("roundNum");
                        team1Name = o.getJSONObject("topRow").getString("teamId");
                        if(team1Name.equals("1610612762")) { team1Name = "Jazz";}
                        if(team1Name.equals("1610612743")) { team1Name = "Nuggets";}
                        if(team1Name.equals("1610612738")) { team1Name = "Celtics";}
                        if(team1Name.equals("1610612755")) { team1Name = "76ers";}
                        if(team1Name.equals("1610612742")) { team1Name = "Mavericks";}
                        if(team1Name.equals("1610612746")) { team1Name = "Clippers";}
                        if(team1Name.equals("1610612761")) { team1Name = "Raptors";}
                        if(team1Name.equals("1610612751")) { team1Name = "Nets";}
                        if(team1Name.equals("1610612748")) { team1Name = "Heat";}
                        if(team1Name.equals("1610612754")) { team1Name = "Pacers";}
                        if(team1Name.equals("1610612749")) { team1Name = "Bucks";}
                        if(team1Name.equals("1610612753")) { team1Name = "Magic";}
                        if(team1Name.equals("1610612747")) { team1Name = "Lakers";}
                        if(team1Name.equals("1610612757")) { team1Name = "Trail Blazers";}
                        if(team1Name.equals("1610612760")) { team1Name = "Thunder";}
                        if(team1Name.equals("1610612745")) { team1Name = "Rockets";}

                        team2Name = o.getJSONObject("bottomRow").getString("teamId");
                        if(team2Name.equals("1610612762")) { team2Name = "Jazz";}
                        if(team2Name.equals("1610612743")) { team2Name = "Nuggets";}
                        if(team2Name.equals("1610612738")) { team2Name = "Celtics";}
                        if(team2Name.equals("1610612755")) { team2Name = "76ers";}
                        if(team2Name.equals("1610612742")) { team2Name = "Mavericks";}
                        if(team2Name.equals("1610612746")) { team2Name = "Clippers";}
                        if(team2Name.equals("1610612761")) { team2Name = "Raptors";}
                        if(team2Name.equals("1610612751")) { team2Name = "Nets";}
                        if(team2Name.equals("1610612748")) { team2Name = "Heat";}
                        if(team2Name.equals("1610612754")) { team2Name = "Pacers";}
                        if(team2Name.equals("1610612749")) { team2Name = "Bucks";}
                        if(team2Name.equals("1610612753")) { team2Name = "Magic";}
                        if(team2Name.equals("1610612747")) { team2Name = "Lakers";}
                        if(team2Name.equals("1610612757")) { team2Name = "Trail Blazers";}
                        if(team2Name.equals("1610612760")) { team2Name = "City Thunder";}
                        if(team2Name.equals("1610612745")) { team2Name = "Rockets";}

                        wins1 = o.getJSONObject("topRow").getString("wins");
                        wins2 = o.getJSONObject("bottomRow").getString("wins");
                        confName = o.getString("confName");
                        summaryStatusText = o.getString("summaryStatusText");



                        PlayoffModel model = new PlayoffModel(round, team1Name, team2Name, "  " + wins1,"  " + wins2, confName, summaryStatusText);
                        modelList.add(model);
                    }
                    mAdapter = new PlayoffsAdapter(modelList, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                }
                catch (Exception e){
                    Toast.makeText(PlayoffsActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PlayoffsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}