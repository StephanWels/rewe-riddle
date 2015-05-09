package com.ecomhack.riddle;

import java.util.HashSet;
import java.util.Set;

/**
 * Android documentation says, it's fine to keep the state in static variables :)
 */
public class ApplicationState {


    private static boolean gameIsActive = false;

    private static Set<String> nearProducts = new HashSet<>();

    private static int currentRiddle = 0;
    private static int numberTriesLeft = 3;


    public static void nextRiddle(){
        currentRiddle++;
        numberTriesLeft=3;
    }

    public static String getCurrentRiddle() {
        return "Riddle " + currentRiddle;
    }

    public static boolean existsNextRiddle(){
        return currentRiddle<2;
    }

    public static void startNewGame() {
        gameIsActive=true;
        currentRiddle=0;
        numberTriesLeft=3;
    }

    public static void setNearProducts(Set<String> value) {
        nearProducts = value;
    }

    public static Set<String> getNearProducts() {
        return nearProducts;
    }

    public static boolean isGameActive() {
        return gameIsActive;
    }

    public static void setGameIsActive(boolean value) {
        gameIsActive = value;
    }

    public static void wrongAnswer() {
        numberTriesLeft--;
    }

    public static boolean hasTriesLeft() {
        return numberTriesLeft>0;
    }

    public static int getNumberTriesLeft() {
        return numberTriesLeft;
    }
}
