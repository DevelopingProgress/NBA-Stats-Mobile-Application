package com.example.nbastats.models;

public class PlayersModel {
    String team, firstName, lastName, jersey, min, points, totReb, assists, steals, blocks;

    public PlayersModel(String team, String firstName, String lastName, String jersey, String min, String points, String totReb, String assists, String steals, String blocks) {
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jersey = jersey;
        this.points = points;
        this.min = min;
        this.totReb = totReb;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJersey() {
        return jersey;
    }

    public String getPoints() {
        return points;
    }

    public String getMin() {
        return min;
    }

    public String getTotReb() {
        return totReb;
    }

    public String getAssists() {
        return assists;
    }

    public String getSteals() {
        return steals;
    }

    public String getBlocks() {
        return blocks;
    }
}
