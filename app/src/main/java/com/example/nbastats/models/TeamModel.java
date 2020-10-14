package com.example.nbastats.models;

public class TeamModel {
    private String teamName, teamNickName, teamTricode, teamWin, teamLoss, winPercentage, teamStreak;

    public TeamModel(String teamName, String teamNickName, String teamTricode, String teamWin, String teamLoss, String winPercentage, String teamStreak) {
        this.teamName = teamName;
        this.teamNickName = teamNickName;
        this.teamTricode = teamTricode;
        this.teamWin = teamWin;
        this.teamLoss = teamLoss;
        this.winPercentage = winPercentage;
        this.teamStreak = teamStreak;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamNickName() {
        return teamNickName;
    }

    public String getTeamTricode() {
        return teamTricode;
    }

    public String getTeamWin() {
        return teamWin;
    }

    public String getTeamLoss() {
        return teamLoss;
    }

    public String getWinPercentage() {
        return winPercentage;
    }

    public String getTeamStreak() {
        return teamStreak;
    }
}
