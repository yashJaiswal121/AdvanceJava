package com.reflectionDemos.reflection4.init;

import com.reflectionDemos.reflection4.game.Game;

import java.lang.reflect.InvocationTargetException;

public class Reflection4 {

    //Dependency Injection Using Reflection Example
    //Instantiation of Package-Private classes from another Package
    //AutoCreation of Objects at Application startup

    //Example : Creation of Game Object Using self-made DependencyInjectionEngine via Reflection.
    // Game has dependency of 2 Teams,and a Venue where it can be played.
    // Team has dependency 2 Players and Venue has dependency of an Address.

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        Game game = DependencyInjectionEngine.createObjectRecursively(Game.class);
        System.out.println(game);
    }

}
