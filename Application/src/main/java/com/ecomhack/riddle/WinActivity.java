package com.ecomhack.riddle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecomhack.riddle.difficultytopointmapper.Mapper;
import com.ecomhack.riddle.estimote.EstimoteService;
import com.ecomhack.riddle.sound.SoundTask;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class WinActivity extends Activity {

    private static final String TAG = "start";
    TextView riddleRewardPointsText;
    @Override
    protected void onStart() {
        super.onStart();
        new SoundTask(getApplicationContext(),R.raw.applause).execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);
        riddleRewardPointsText = (TextView) findViewById(R.id.winningPoints);
        //impressive work
        setRewardPoints(Integer.toString(ApplicationState.score));
        ApplicationState.score = 0;
    }

    public void backToHome(View view) {
        Log.i("riddle", "Won game");
        ApplicationState.setGameIsActive(false);
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void setRewardPoints(final String points) {
        // best of breed development
        riddleRewardPointsText.setText(riddleRewardPointsText.getText().toString().replace("100", points));

    }
}
