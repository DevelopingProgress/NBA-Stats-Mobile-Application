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
import com.example.nbastats.adapters.MatchesAdapter;
import com.example.nbastats.R;
import com.example.nbastats.adapters.TodayMatchesAdapter;
import com.example.nbastats.models.MatchesModel;
import com.example.nbastats.models.TodayMatchesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TodayMatchesActivity extends AppCompatActivity {

    private String url = "https://data.nba.net/prod/v2/";

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private List<TodayMatchesModel> modelList;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Today Matches");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add("Dates:");
        categories.add("Yesterday");



        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Dates:")){

                }
                else{
                    String item = parent.getItemAtPosition(position).toString();

                    Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

                    if(parent.getItemAtPosition(position).equals("Yesterday")){

                        Intent intent = new Intent(TodayMatchesActivity.this, MatchesActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        modelList = new ArrayList<>();

        Date mydate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(mydate);
        url = url + date + "/scoreboard.json";
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
                    JSONArray jsonArray = new JSONObject(response).getJSONArray("games");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject o = jsonArray.getJSONObject(i);
                        String gameId, vTeamTri, vTeamScore, hTeamTri, hTeamScore,startDateEastern, startTimeUTC ;
                        gameId = o.getString("gameId");
                        vTeamTri = o.getJSONObject("vTeam").getString("triCode");
                        vTeamScore = o.getJSONObject("vTeam").getString("score");
                        hTeamTri = o.getJSONObject("hTeam").getString("triCode");
                        hTeamScore = o.getJSONObject("hTeam").getString("score");
                        startDateEastern = o.getString("startDateEastern");
                        startTimeUTC = o.getString("startTimeUTC");
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                        Date date = format1.parse(startTimeUTC);
                        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
                        format2.setTimeZone(TimeZone.getTimeZone("GMT+2"));
                        String dateTime = format2.format(date);


                        TodayMatchesModel model = new TodayMatchesModel(gameId, vTeamTri, vTeamScore, hTeamTri, hTeamScore,startDateEastern, dateTime);
                        modelList.add(model);
                    }
                    mAdapter = new TodayMatchesAdapter(modelList, getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);

                }
                catch (Exception e){
                    Toast.makeText(TodayMatchesActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TodayMatchesActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();
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