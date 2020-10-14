package com.example.nbastats.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nbastats.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MatchDetailActivity extends AppCompatActivity {

    TextView VteamTv, VteamScoreTv, Q1vScoreTv, Q2vScoreTv, Q3vScoreTv, Q4vScoreTv, HteamTv, HteamScoreTv, Q1hScoreTv,
            Q2hScoreTv, Q3hScoreTv, Q4hScoreTv, dateTV, interesting;

    String url = "https://data.nba.net/prod/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Match Detail");

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("match_id");
        String date = intent.getStringExtra("date");
        url = url + date + "/" + id + "_mini_boxscore.json";

        VteamTv = findViewById(R.id.VteamTv);
        VteamScoreTv = findViewById(R.id.VteamScoreTv);
        Q1vScoreTv = findViewById(R.id.Q1vScoreTv);
        Q2vScoreTv = findViewById(R.id.Q2vScoreTv);
        Q3vScoreTv = findViewById(R.id.Q3vScoreTv);
        Q4vScoreTv = findViewById(R.id.Q4vScoreTv);
        HteamTv = findViewById(R.id.HteamTv);
        HteamScoreTv = findViewById(R.id.HteamScoreTv);
        Q1hScoreTv = findViewById(R.id.Q1hScoreTv);
        Q2hScoreTv = findViewById(R.id.Q2hScoreTv);
        Q3hScoreTv = findViewById(R.id.Q3hScoreTv);
        Q4hScoreTv = findViewById(R.id.Q4hScoreTv);
        dateTV = findViewById(R.id.date);
        interesting = findViewById(R.id.interesting);

        dateTV.setText(date);

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
                    String gameId = jsonObject.getJSONObject("basicGameData").getString("gameId");
                    String text = jsonObject.getJSONObject("basicGameData").getJSONObject("nugget").getString("text");
                    String startTimeUTC = jsonObject.getJSONObject("basicGameData").getString("startTimeUTC");

                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    Date date = format1.parse(startTimeUTC);
                    SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
                    format2.setTimeZone(TimeZone.getTimeZone("GMT+1"));
                    String dateTime = format2.format(date);


                    String vTeamTri = jsonObject.getJSONObject("basicGameData").getJSONObject("vTeam").getString("triCode");
                    String vTeamScore = jsonObject.getJSONObject("basicGameData").getJSONObject("vTeam").getString("score");

                    JSONArray vTeamArr = jsonObject.getJSONObject("basicGameData").getJSONObject("vTeam").getJSONArray("linescore");
                    for(int i=0; i < vTeamArr.length(); i++){

                        String q1 = vTeamArr.getJSONObject(0).getString("score");
                        String q2 = vTeamArr.getJSONObject(1).getString("score");
                        String q3 = vTeamArr.getJSONObject(2).getString("score");
                        String q4 = vTeamArr.getJSONObject(3).getString("score");

                        Q1vScoreTv.setText(q1);
                        Q2vScoreTv.setText(q2);
                        Q3vScoreTv.setText(q3);
                        Q4vScoreTv.setText(q4);
                    }


                    String hTeamTri = jsonObject.getJSONObject("basicGameData").getJSONObject("hTeam").getString("triCode");
                    String hTeamScore = jsonObject.getJSONObject("basicGameData").getJSONObject("hTeam").getString("score");
                    JSONArray hTeamArr = jsonObject.getJSONObject("basicGameData").getJSONObject("hTeam").getJSONArray("linescore");
                    for(int i=0; i < hTeamArr.length(); i++){

                        String q1 = hTeamArr.getJSONObject(0).getString("score");
                        String q2 = hTeamArr.getJSONObject(1).getString("score");
                        String q3 = hTeamArr.getJSONObject(2).getString("score");
                        String q4 = hTeamArr.getJSONObject(3).getString("score");

                        Q1hScoreTv.setText(q1);
                        Q2hScoreTv.setText(q2);
                        Q3hScoreTv.setText(q3);
                        Q4hScoreTv.setText(q4);
                    }

                    VteamTv.setText(vTeamTri);
                    VteamScoreTv.setText(vTeamScore);

                    HteamTv.setText(hTeamTri);
                    HteamScoreTv.setText(hTeamScore);

                    dateTV.setText(dateTime);
                    interesting.setText(text);

                    
                } catch (Exception e){

                    Toast.makeText(MatchDetailActivity.this, ""+e.getMessage() , Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MatchDetailActivity.this, "Error" + error.toString(), Toast.LENGTH_LONG).show();
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
