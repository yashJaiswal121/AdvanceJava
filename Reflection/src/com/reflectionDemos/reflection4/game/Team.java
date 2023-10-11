package com.reflectionDemos.reflection4.game;

import java.util.concurrent.atomic.AtomicInteger;

class Team {
    private Player player1;
    private Player player2;
    private String teamName;
    private static AtomicInteger integer = new AtomicInteger();

    public Team(Player player1,Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        teamName = "TEAM-"+integer.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Team{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", teamName="+ teamName +
                '}';
    }
}
