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
import com.example.nbastats.adapters.TeamsAdapter;
import com.example.nbastats.models.TeamModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StandingsActivity extends AppCompatActivity {

    private String url = "https://data.nba.net/prod/v1/current/standings_all.json";

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private List<TeamModel> modelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Standings");

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
                    JSONArray jsonArray = new JSONObject(response).getJSONObject("league").getJSONObject("standard").getJSONArray("teams");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        String teamName, teamNickName, teamTricode, teamWin, teamLoss, winPercentage, teamStreak;
                        teamName = o.getJSONObject("teamSitesOnly").getString("teamName");
                        teamNickName = o.getJSONObject("teamSitesOnly").getString("teamNickname");
                        teamTricode = o.getJSONObject("teamSitesOnly").getString("teamTricode");
                        teamWin = o.getString("win");
                        teamLoss = o.getString("loss");
                        winPercentage = o.getString("winPctV2");
                        teamStreak = o.getJSONObject("teamSitesOnly").getString("streakText");


                        while(teamName.length() < 13)

                        {
                            teamName += " ";
                        }

                        while(teamNickName.length() < 13)

                        {
                            teamNickName += " ";
                        }
                        TeamModel model;
                        if(Double.parseDouble(winPercentage) % 1 == 0)
                        {
                            winPercentage+="  ";
                        }
                         model = new TeamModel(teamName, teamNickName, teamTricode + "  ",
                                 teamWin+ "  ", teamLoss+ "  ", winPercentage+ "  ", teamStreak+ "  ");
                        modelList.add(model);
                    }
                    mAdapter = new TeamsAdapter(modelList, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                }
                catch (Exception e){
                    Toast.makeText(StandingsActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StandingsActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
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
