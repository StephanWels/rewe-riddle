package com.ecomhack.riddle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.basicnotifications.R;

import java.util.HashSet;

import static com.ecomhack.riddle.ConstantsStateKeys.*;

public class RiddleActivity extends Activity {

    private static final String TAG = "Riddle";

    private final String productToFind = "Product White";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riddle1);
    }

    public void checkWhetherCorrect(View view) {
        Log.i("riddle", "Am I right?");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        if (pref.getStringSet(KEY_NEAR_PRODUCTS, new HashSet<String>()).contains(productToFind)){
            Log.i("riddle", "YES!");
        } else {
            Log.i("riddle", "NO!");
        }
    }
}
