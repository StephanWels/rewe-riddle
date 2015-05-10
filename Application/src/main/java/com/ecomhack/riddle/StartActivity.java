package com.ecomhack.riddle;

import android.app.Activity;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;

import com.ecomhack.riddle.estimote.EstimoteService;

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
    }

    public void startBreakfastChallenge(View view) {
        /*
        viewIntent.putExtra(EXTRA_EVENT_ID, notificationId);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, view, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("Riddle")
                        .setContentText("Supermarket")
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, notificationBuilder.build());
        */
        Log.i("riddle", "start breakfast");

        ApplicationState.startChallenge(BREAKFAST_CHALLENGE_ID);

        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }
}
