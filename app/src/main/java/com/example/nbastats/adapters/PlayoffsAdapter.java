package com.example.nbastats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbastats.R;
import com.example.nbastats.models.PlayoffModel;
import com.example.nbastats.models.TeamModel;

import java.util.List;

public class PlayoffsAdapter extends RecyclerView.Adapter<PlayoffsAdapter.ViewHolder> {
    private List<PlayoffModel> modelList;
    private Context mcontext;

    public PlayoffsAdapter(List<PlayoffModel> modelList, Context applicationContext) {
        this.modelList = modelList;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public PlayoffsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_playoffs, viewGroup, false);
        return new PlayoffsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayoffsAdapter.ViewHolder holder, int position) {
        final PlayoffModel model = modelList.get(position);
        holder.round.setText(model.getRound());
        holder.team1Name.setText(model.getTeam1Name());
        holder.team2Name.setText(model.getTeam2Name());
        holder.wins1.setText(model.getWins1());
        holder.wins2.setText(model.getWins2());
        holder.confName.setText(model.getConfName());
        holder.summaryStatusText.setText(model.getSummaryStatusText());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView round, team1Name, team2Name, wins1, wins2, confName, summaryStatusText;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            round = itemView.findViewById(R.id.round);
            team1Name = itemView.findViewById(R.id.team1);
            team2Name = itemView.findViewById(R.id.team2);
            wins1 = itemView.findViewById(R.id.score1);
            wins2 = itemView.findViewById(R.id.score2);
            confName = itemView.findViewById(R.id.conference);
            summaryStatusText = itemView.findViewById(R.id.note);
            cardView = itemView.findViewById(R.id.cardViewPlayoffs);
        }
    }
}



