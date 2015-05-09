package com.ecomhack.riddle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ecomhack.riddle.estimote.EstimoteService;
import com.ecomhack.riddle.sphere.SphereAuthenticationTask;
import com.ecomhack.riddle.sphere.SphereChallengeQueryTask;
import com.ecomhack.riddle.sphere.SphereProductQueryTask;
import com.ecomhack.riddle.sphere.models.AuthResponse;
import com.ecomhack.riddle.sphere.models.Challenge;
import com.ecomhack.riddle.sphere.models.Product;
import com.ecomhack.riddle.sphere.models.QueryResult;

import java.util.concurrent.ExecutionException;

public class StartActivity extends Activity {
    
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
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }

    public void startBreakfastChallenge(View view){
        Log.i("riddle", "start breakfast");

        ApplicationState.startNewGame();

        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }
}
