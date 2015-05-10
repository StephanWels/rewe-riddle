package com.ecomhack.riddle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ecomhack.riddle.estimote.EstimoteService;
import com.ecomhack.riddle.sphere.models.Challenge;

public class StartActivity extends Activity {

    private static final String BREAKFAST_CHALLENGE_ID = "945942bf-dbb5-4175-807d-0c40da4dcb7e";

    @Override
    protected void onStart() {
        super.onStart();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                // Bluetooth is not enable
                Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                btIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(btIntent);
            } else {
                // Bluetooth is already enabled
                getApplicationContext().startService(new Intent(getApplicationContext(),
                        EstimoteService.class));
            }
        }
        ApplicationState.stopChallenge();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        LinearLayout view = (LinearLayout) findViewById(R.id.challenges);
        for (Challenge challenge : ApplicationState.getChallenges()) {
            addButtonToView(view, challenge);
        }
    }

    private void addButtonToView(LinearLayout view, final Challenge challenge) {
        Button button = new Button(getApplicationContext());
        button.setText(challenge.getName().de());
        //button.setBackgroundColor(0xFFff9230);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("riddle", "start breakfast");
                ApplicationState.startChallenge(challenge.getId());
                Intent intent = new Intent(v.getContext(), RiddleActivity.class);
                startActivity(intent);
            }
        });
        view.addView(button);
    }
}
