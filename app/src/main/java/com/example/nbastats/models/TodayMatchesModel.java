package com.example.nbastats.models;

public class TodayMatchesModel {
    private String gameIdToday, vTeamTriToday, vTeamScoreToday, hTeamTriToday, hTeamScoreToday,dateToday, datetopassToday;

    public TodayMatchesModel(String gameIdToday, String vTeamTriToday, String vTeamScoreToday, String hTeamTriToday, String hTeamScoreToday,  String dateToday, String datetopassToday) {
        this.gameIdToday = gameIdToday;
        this.vTeamTriToday = vTeamTriToday;
        this.vTeamScoreToday = vTeamScoreToday;
        this.hTeamTriToday = hTeamTriToday;
        this.hTeamScoreToday = hTeamScoreToday;
        this.dateToday = dateToday;
        this.datetopassToday = datetopassToday;
    }

    public String getGameIdToday() {
        return gameIdToday;
    }

    public String getvTeamTriToday() {
        return vTeamTriToday;
    }

    public String getvTeamScoreToday() {
        return vTeamScoreToday;
    }

    public String gethTeamTriToday() {
        return hTeamTriToday;
    }

    public String gethTeamScoreToday() {
        return hTeamScoreToday;
    }

    public String getDateToday() {
        return dateToday;
    }

    public String getDatetopassToday() {
        return datetopassToday;
    }
}