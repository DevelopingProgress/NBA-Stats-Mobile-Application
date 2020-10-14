package com.example.nbastats.models;

public class PlayoffModel {

    private String round, team1Name, team2Name, wins1, wins2, confName, summaryStatusText;

    public PlayoffModel(String round, String team1Name, String team2Name,String wins1, String wins2,String confName, String summaryStatusText) {
        this.round = round;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.wins1 = wins1;
        this.wins2 = wins2;
        this.confName = confName;
        this.summaryStatusText = summaryStatusText;
    }

    public String getRound() {
        return round;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public String getWins1() {
        return wins1;
    }
    public String getWins2() {
        return wins2;
    }


    public String getConfName() {
        return confName;
    }

    public String getSummaryStatusText() {
        return summaryStatusText;
    }
}


