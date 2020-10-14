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
import com.example.nbastats.models.PlayersModel;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {
    private List<PlayersModel> modelList;
    private Context mcontext;

    public PlayersAdapter(List<PlayersModel> modelList, Context applicationContext) {
        this.modelList = modelList;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public PlayersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_boxscore, viewGroup, false);
        return new PlayersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersAdapter.ViewHolder holder, int position) {
        final PlayersModel model = modelList.get(position);
        holder.VteamName.setText(model.getTeam());
        holder.VteamPName.setText(model.getFirstName());
        holder.VteamLastName.setText(model.getLastName());
        holder.VteamNR.setText(model.getJersey());
        holder.VteamMIN.setText(model.getPoints());
        holder.VteamPTS.setText(model.getMin());
        holder.VteamRBS.setText(model.getTotReb());
        holder.VteamATS.setText(model.getAssists());
        holder.VteamBLK.setText(model.getBlocks());
        holder.VteamSTL.setText(model.getSteals());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView VteamName, VteamPName, VteamLastName, VteamNR, VteamMIN, VteamPTS, VteamRBS, VteamATS, VteamBLK ,VteamSTL;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            VteamName = itemView.findViewById(R.id.VteamName);
            VteamPName = itemView.findViewById(R.id.VteamPName);
            VteamLastName = itemView.findViewById(R.id.VteamLastName);
            VteamNR = itemView.findViewById(R.id.VteamNR);
            VteamMIN = itemView.findViewById(R.id.VteamMIN);
            VteamPTS = itemView.findViewById(R.id.VteamPTS);
            VteamRBS = itemView.findViewById(R.id.VteamRBS);
            VteamATS = itemView.findViewById(R.id.VteamATS);
            VteamBLK = itemView.findViewById(R.id.VteamBLK);
            VteamSTL = itemView.findViewById(R.id.VteamSTL);
            cardView = itemView.findViewById(R.id.cardViewBoxscore);
        }
    }
}

