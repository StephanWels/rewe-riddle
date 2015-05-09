package com.ecomhack.riddle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ecomhack.riddle.estimote.EstimoteService;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class LoseActivity extends Activity {

    private static final String TAG = "start";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lose);
    }

    public void startOver(View view) {
        Log.i("riddle", "Lost game");
        //TODO: set back to first riddle
        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }
}
