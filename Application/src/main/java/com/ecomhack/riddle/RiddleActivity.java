package com.ecomhack.riddle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android.basicnotifications.R;


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
        if (ApplicationState.getNearProducts().contains(productToFind)) {
            Log.i("riddle", "YES!");
            if (ApplicationState.existsNextRiddle()) {
                ApplicationState.nextRiddle();
                Intent intent = new Intent(this, CorrectActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, WinActivity.class);
                startActivity(intent);
            }

        } else {
            Log.i("riddle", "NO!");
            Intent intent = new Intent(this, LoseActivity.class);
            startActivity(intent);
        }
    }

}
