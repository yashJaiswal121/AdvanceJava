package com.reflectionDemos.reflection4.game;

//Except for this Class, all other classes in this package are package-private.
//So Only this class can be used outside this Package via any Client or another Package
public class Game {

    private Team team1;
    private Team team2;
    private Venue venue;

    Game(Team team1,Team team2,Venue venue){
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Game{" +
                "team1=" + team1 +
                ", team2=" + team2 +
                ", venue=" + venue +
                '}';
    }
}
