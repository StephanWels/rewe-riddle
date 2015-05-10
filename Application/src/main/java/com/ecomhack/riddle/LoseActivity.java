package com.ecomhack.riddle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ecomhack.riddle.estimote.EstimoteService;
import com.ecomhack.riddle.sound.SoundTask;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class LoseActivity extends Activity {

    private static final String TAG = "start";

    @Override
    protected void onStart() {
        super.onStart();

        new SoundTask(getApplicationContext(), R.raw.crowd_boo).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);
    }

    public void startOver(View view) {
        Log.i("riddle", "Lost game");
        ApplicationState.reStartChallenge();
        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }

    public void backToMain(View view){
        Intent start = new Intent(this, StartActivity.class);
        startActivity(start);
    }
}
