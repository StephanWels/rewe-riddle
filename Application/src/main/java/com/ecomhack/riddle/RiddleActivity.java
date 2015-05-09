package com.ecomhack.riddle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecomhack.riddle.activityinterfaces.Riddle;
import com.example.android.basicnotifications.R;


public class RiddleActivity extends Activity implements Riddle {

    private static final String TAG = "Riddle";

    private final String productToFind = "Product White";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riddle1);
    }

    public void checkWhetherCorrect(View view) {
        Log.i("riddle", "Am I right?");
        if (ApplicationState.nearProducts.contains(productToFind)){
            Log.i("riddle", "YES!");
            Intent intent = new Intent(this, CorrectActivity.class);
            startActivity(intent);
        } else {
            Log.i("riddle", "NO!");
        }
    }

    @Override
    public void setTitle(String title) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(title);
    }

    @Override
    public void setBody(String body) {

    }

    @Override
    public void setReward(String reward) {

    }
}
