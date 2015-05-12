package com.ecomhack.riddle;

import android.util.Log;

import com.ecomhack.riddle.sphere.ChallengeNotFoundException;
import com.ecomhack.riddle.sphere.SphereAuthenticationTask;
import com.ecomhack.riddle.sphere.SphereChallengeQueryTask;
import com.ecomhack.riddle.sphere.SphereProductQueryTask;
import com.ecomhack.riddle.sphere.models.AuthResponse;
import com.ecomhack.riddle.sphere.models.Challenge;
import com.ecomhack.riddle.sphere.models.Product;
import com.ecomhack.riddle.sphere.models.QueryResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Android documentation says, it's fine to keep the state in static variables :)
 */
public class ApplicationState {
    private static boolean gameIsActive = false;

    private static List<Challenge> challenges = new ArrayList<>();
    private static Map<String, List<Product>> products = fetchChallenges();

    private static Set<String> nearProducts = new HashSet<>();

    private static Map<String, List<Boolean>> nearWaitForAdBeacons = new HashMap<>();

    private static Set<String> activeWaitForAdBeacons = new HashSet<>();
    private static Set<String> activatedWaitForAdBeacons = new HashSet<>();

    private static int currentRiddle = 0;
    private static int numberTriesLeft = 3;
    private static String activeChallenge = null;

    public static int score = 0;

    public static void nextRiddle(){
        currentRiddle++;
        numberTriesLeft=3;
    }

    public static Product getCurrentRiddleObjective(){
        return products.get(activeChallenge).get(currentRiddle);
    }

    public static String getCurrentRiddleName() {
        return "Riddle No " + (1+currentRiddle);
    }

    public static boolean existsNextRiddle(){
        return currentRiddle<2;
    }

    public static void startChallenge(String challengeId) {
        activeChallenge = challengeId;
        gameIsActive=true;
        currentRiddle=0;
        numberTriesLeft=3;
    }

    public static void setNearProducts(Set<String> closeBeacons) {

        // store for each beacon how often they were detected in the last 30 detection periods (seconds)
        for (String beacon : closeBeacons){
            if (!nearWaitForAdBeacons.containsKey(beacon)){
                nearWaitForAdBeacons.put(beacon, new LinkedList<Boolean>());
            }
            nearWaitForAdBeacons.get(beacon).add(true);
        }
        for (String beacon : nearWaitForAdBeacons.keySet()){
            if (!closeBeacons.contains(beacon)){
                nearWaitForAdBeacons.get(beacon).add(false);
            }
        }
        for (String beacon : nearWaitForAdBeacons.keySet()){
            if (nearWaitForAdBeacons.get(beacon).size()>20){
                nearWaitForAdBeacons.get(beacon).remove(0);
            }
        }

        for (Map.Entry<String, List<Boolean>> beaconEntry : nearWaitForAdBeacons.entrySet()){
            int size = beaconEntry.getValue().size();
            if (beaconEntry.getValue().get(size-1) || beaconEntry.getValue().get(size-2)){
                nearProducts.add(beaconEntry.getKey());
            }
        }

        activeWaitForAdBeacons.clear();
        // a waitForAdBeacon is active, if it was seen at least in 25 of the last 30 detection periods
        for (Map.Entry<String, List<Boolean>> beaconEntry : nearWaitForAdBeacons.entrySet()){
            int activePeriodsCounter = 0;
            for (Boolean active : beaconEntry.getValue()){
                if (active){
                    activePeriodsCounter++;
                }
            }
            Log.i("riddle", "Set active count for beacon " + beaconEntry.getKey() + " to " + activePeriodsCounter );
            if (activePeriodsCounter>=15){
                activeWaitForAdBeacons.add(beaconEntry.getKey());
            }
        }
        activeWaitForAdBeacons.removeAll(activatedWaitForAdBeacons);
    }

    public static Set<String> getActiveWaitForAdBeacons() {

        return activeWaitForAdBeacons;
    }

    public static void addReceivedSpecialDiscount(String specialDiscountBeacon) {
        activatedWaitForAdBeacons.add(specialDiscountBeacon);
    }

    public static List<Challenge> getChallenges() {
        return challenges;
    }

    public static List<Product> getProducts(String challengeId){
        if (products.containsKey(challengeId)) {
            return products.get(challengeId);
        }
        throw new ChallengeNotFoundException(challengeId);
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

    private static Map<String, List<Product>> fetchChallenges() {
        final Map<String, List<Product>> challengeMap = new HashMap<>();
        try {
            final AuthResponse authResponse = new SphereAuthenticationTask().execute(null, null).get();
            challenges = new SphereChallengeQueryTask(authResponse).execute().get().getResults();
            for (Challenge challenge : challenges) {
                final QueryResult<Product> products = new SphereProductQueryTask(authResponse, challenge.getId()).execute().get();
                challengeMap.put(challenge.getId(), products.getResults());
            }
        } catch (ExecutionException | InterruptedException e) {
            Log.e("riddle", "Could not fetch data from SPHERE.IO", e);
        }
        return challengeMap;
    }

    public static void reStartChallenge() {
        currentRiddle=0;
        numberTriesLeft=3;
    }

    public static void stopChallenge() {
        activeChallenge=null;
        reStartChallenge();
    }
}
