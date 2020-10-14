package com.example.nbastats.models;

public class MatchesModel {
    private String gameId, vTeamTri, vTeamScore, hTeamTri, hTeamScore,date, datetopass;

    public MatchesModel(String gameId, String vTeamTri, String vTeamScore, String hTeamTri, String hTeamScore,  String date, String datetopass) {
        this.gameId = gameId;
        this.vTeamTri = vTeamTri;
        this.vTeamScore = vTeamScore;
        this.hTeamTri = hTeamTri;
        this.hTeamScore = hTeamScore;
        this.date = date;
        this.datetopass = datetopass;
    }

    public String getDatetopass() {
        return datetopass;
    }

    public String getGameId() {
        return gameId;
    }

    public String getvTeamTri() {
        return vTeamTri;
    }

    public String getvTeamScore() {
        return vTeamScore;
    }

    public String gethTeamTri() {
        return hTeamTri;
    }

    public String gethTeamScore() {
        return hTeamScore;
    }


    public String getDate() {
        return date;
    }
}