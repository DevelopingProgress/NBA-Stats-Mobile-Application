package com.example.nbastats.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nbastats.adapters.PlayersAdapter;
import com.example.nbastats.R;
import com.example.nbastats.models.PlayersModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BoxscoreActivity extends AppCompatActivity {


    String url = "https://data.nba.net/prod/v1/";

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private List<PlayersModel> modelList;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setVisibility(View.GONE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Box Score");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("match_id");
        String date = intent.getStringExtra("date");
        url = url + date + "/" + id + "_boxscore.json";

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        modelList = new ArrayList<>();


        loadData();



    }

    private void loadData() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String vteamName = jsonObject.getJSONObject("basicGameData").getJSONObject("vTeam").getString("triCode");
                    String hteamName = jsonObject.getJSONObject("basicGameData").getJSONObject("hTeam").getString("triCode");
                    String vteamId = jsonObject.getJSONObject("basicGameData").getJSONObject("vTeam").getString("teamId");
                    JSONArray ArrPlayers = jsonObject.getJSONObject("stats").getJSONArray("activePlayers");

                    for(int i = 0; i < ArrPlayers.length(); i++){
                        JSONObject o = ArrPlayers.getJSONObject(i);
                        String teamId, firstName, lastName, jersey, points, min, totReb, assists, steals, blocks;
                        teamId = o.getString("teamId");
                        if(teamId.equals(vteamId)){
                            teamId = vteamName;
                        }
                        else{
                            teamId = hteamName;
                        }
                        firstName = o.getString("firstName");
                        lastName = o.getString("lastName");
                        jersey = o.getString("jersey");
                        points = o.getString("points");
                        min = o.getString("min");
                        totReb = o.getString("totReb");
                        assists = o.getString("assists");
                        steals = o.getString("steals");
                        blocks = o.getString("blocks");

                        if(min.length()==4) min+=" ";

                        PlayersModel model = new PlayersModel(teamId, firstName, lastName, jersey, points, min,totReb, assists, steals, blocks);
                        modelList.add(model);

                    }
                    mAdapter = new PlayersAdapter(modelList, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                } catch (Exception e){

                    Toast.makeText(BoxscoreActivity.this, ""+e.getMessage() , Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BoxscoreActivity.this, "Error" + error.toString(), Toast.LENGTH_LONG).show();
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
