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

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder>{

    private List<MatchesModel> modelList;
    private Context mcontext;

    public MatchesAdapter(List<MatchesModel> modelList, Context applicationContext) {
        this.modelList = modelList;
        this.mcontext = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.matches_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MatchesModel model = modelList.get(position);
        holder.vTeamTv.setText(model.getvTeamTri());
        holder.vTeamScoreTv.setText(model.getvTeamScore());
        holder.hTeamTv.setText(model.gethTeamTri());
        holder.hTeamScoreTv.setText(model.gethTeamScore());
        holder.date1.setText(model.getDatetopass());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String matchId = model.getGameId();
                final String date = model.getDate();

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

        TextView vTeamTv, vTeamScoreTv, hTeamTv, hTeamScoreTv, date1;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vTeamTv = itemView.findViewById(R.id.VteamTv);
            vTeamScoreTv = itemView.findViewById(R.id.VteamScoreTv);
            hTeamTv = itemView.findViewById(R.id.HteamTv);
            hTeamScoreTv = itemView.findViewById(R.id.HteamScoreTv);
            date1 = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
