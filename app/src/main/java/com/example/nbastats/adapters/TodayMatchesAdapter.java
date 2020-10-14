package com.example.nbastats.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbastats.R;
import com.example.nbastats.activities.BoxscoreActivity;
import com.example.nbastats.activities.MatchDetailActivity;
import com.example.nbastats.models.MatchesModel;
import com.example.nbastats.models.TodayMatchesModel;

import java.util.List;

public class TodayMatchesAdapter extends RecyclerView.Adapter<TodayMatchesAdapter.ViewHolder>{

    private List<TodayMatchesModel> modelList;
    private Context mcontext;

    public TodayMatchesAdapter(List<TodayMatchesModel> modelList, Context applicationContext) {
        this.modelList = modelList;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_today_matches, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TodayMatchesModel model = modelList.get(position);
        holder.vTeamTvToday.setText(model.getvTeamTriToday());
        holder.vTeamScoreTvToday.setText(model.getvTeamScoreToday());
        holder.hTeamTvToday.setText(model.gethTeamTriToday());
        holder.hTeamScoreTvToday.setText(model.gethTeamScoreToday());
        holder.date1Today.setText(model.getDatetopassToday());



        holder.cardViewToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String matchId = model.getGameIdToday();
                final String date = model.getDateToday();

                String[] options = {"Match Detail", "Box Score"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                builder.setTitle("Choose Option");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){

                            Intent intent = new Intent(mcontext, MatchDetailActivity.class );
                            intent.putExtra("match_id", matchId);
                            intent.putExtra("date", date);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mcontext.startActivity(intent);
                        }
                        if(which == 1){

                            Intent intent = new Intent(mcontext, BoxscoreActivity.class );
                            intent.putExtra("match_id", matchId);
                            intent.putExtra("date", date);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mcontext.startActivity(intent);

                        }
                    }
                });
                builder.create().show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView vTeamTvToday, vTeamScoreTvToday, hTeamTvToday, hTeamScoreTvToday, date1Today;
        CardView cardViewToday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vTeamTvToday = itemView.findViewById(R.id.VteamTvToday);
            vTeamScoreTvToday = itemView.findViewById(R.id.VteamScoreTvToday);
            hTeamTvToday = itemView.findViewById(R.id.HteamTvToday);
            hTeamScoreTvToday = itemView.findViewById(R.id.HteamScoreTvToday);
            date1Today = itemView.findViewById(R.id.dateToday);
            cardViewToday = itemView.findViewById(R.id.cardViewTodayMatches);
        }
    }
}
