package com.ecomhack.riddle.estimote;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ecomhack.riddle.ConstantsStateKeys;
import com.estimote.sdk.Beacon;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class NearbyProductDiscovery {

    // should be retrieved dynamically from a database...
    private static HashMap<Integer, String> beaconMajorToProduct;

    static {
        beaconMajorToProduct = new HashMap<>();
        beaconMajorToProduct.put(38286, "Product X");
        beaconMajorToProduct.put(33910, "Product Y");
    }

    private final Context context;

    public NearbyProductDiscovery(final Context context){
        this.context = Objects.requireNonNull(context, "context must not be null");
    }

    public void discoverProducts(List<Beacon> nearbyBeacons){
        Set<String> nearProducts = new HashSet<>();
        for (Beacon beacon : nearbyBeacons){

            if (beaconMajorToProduct.containsKey(beacon.getMajor())){
                nearProducts.add(beaconMajorToProduct.get(beacon.getMajor()));
            }
        }
        storeNearProducts(nearProducts);
    }

    private void storeNearProducts(Set<String> nearProducts) {
        Log.i("riddle", "Nearby Products: " + nearProducts.toString());

        SharedPreferences pref = context.getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(ConstantsStateKeys.KEY_NEAR_PRODUCTS, nearProducts);
        editor.commit(); // commit changes
    }
}