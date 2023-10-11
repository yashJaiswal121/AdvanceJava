package com.reflectionDemos.reflection4.game;

import java.util.concurrent.atomic.AtomicInteger;

class Player {
    private String name;
    private int age;
    private static AtomicInteger integer = new AtomicInteger();

    public Player() {
        this.name = "DEFAULT-PLAYER-NAME"+integer.incrementAndGet();
        this.age = integer.get();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
