package com.ecomhack.riddle;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ecomhack.riddle.sphere.SphereTask;
import com.example.android.basicnotifications.R;

public class RiddleActivity extends Activity {

    private static final String TAG = "Riddle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riddle1);
    }

    public void checkWhetherCorrect(View view) {
        Log.i("riddle", "Am I right?");
    }
}
