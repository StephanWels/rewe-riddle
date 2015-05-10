package com.ecomhack.riddle.difficultytopointmapper;


public class Mapper {

    public static int maptoInt (final String difficulty) {
        if ("easy".equalsIgnoreCase(difficulty)) {
            return 100;
        } else if ("medium".equalsIgnoreCase(difficulty)) {
            return 300;
        } else if ("hard".equalsIgnoreCase(difficulty)) {
            return 500;
        } else if ("impossible".equalsIgnoreCase(difficulty)) {
            return 1000;
        }
        return 0;

    }

    public static String mapToString (final String difficulty) {
        return Integer.toString(maptoInt(difficulty));
    }
}
