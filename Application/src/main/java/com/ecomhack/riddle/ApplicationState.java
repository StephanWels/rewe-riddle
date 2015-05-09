package com.ecomhack.riddle;

import java.util.HashSet;
import java.util.Set;

/**
 * Android documentation says, it's fine to keep the state in static variables :)
 */
public class ApplicationState {

    public static boolean gameIsActive = false;
    public static Set<String> nearProducts = new HashSet<>();

    private static int currentRiddle;


    public static void nextRiddle(){
        currentRiddle++;
    }

    public static String getCurrentRiddle() {
        return "Riddle " + currentRiddle;
    }

    public static boolean existsNextRiddle(){
        return currentRiddle<3;
    }
}
