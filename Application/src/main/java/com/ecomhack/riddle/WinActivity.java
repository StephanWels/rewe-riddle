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
public class WinActivity extends Activity {

    private static final String TAG = "start";

    @Override
    protected void onStart() {
        super.onStart();
        new SoundTask(getApplicationContext(),R.raw.applause).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);
    }

    public void backToHome(View view) {
        Log.i("riddle", "Won game");
        ApplicationState.setGameIsActive(false);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
