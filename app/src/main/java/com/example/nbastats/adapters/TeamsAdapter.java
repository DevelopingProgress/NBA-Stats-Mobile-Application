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
import com.example.nbastats.models.TeamModel;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private List<TeamModel> modelList;
    private Context mcontext;

    public TeamsAdapter(List<TeamModel> modelList, Context applicationContext) {
        this.modelList = modelList;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_standings, viewGroup, false);
        return new TeamsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsAdapter.ViewHolder holder, int position) {
        final TeamModel model = modelList.get(position);
        holder.teamName.setText(model.getTeamName());
        holder.teamNickName.setText(model.getTeamNickName());
        holder.teamTricode.setText(model.getTeamTricode());
        holder.teamWin.setText(model.getTeamWin());
        holder.teamLoss.setText(model.getTeamLoss());
        holder.winPercentage.setText(model.getWinPercentage());
        holder.teamStreak.setText(model.getTeamStreak());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView teamName, teamNickName, teamTricode, teamWin, teamLoss, winPercentage, teamStreak;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.teamName);
            teamNickName = itemView.findViewById(R.id.teamNickName);
            teamTricode = itemView.findViewById(R.id.teamTricode);
            teamWin = itemView.findViewById(R.id.teamWin);
            teamLoss = itemView.findViewById(R.id.teamLoss);
            winPercentage = itemView.findViewById(R.id.winPercentage);
            teamStreak = itemView.findViewById(R.id.teamStreak);
            cardView = itemView.findViewById(R.id.cardViewStandings);
        }
    }
}


