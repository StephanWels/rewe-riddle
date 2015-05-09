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
        try {
            AuthResponse authResponse = new SphereAuthenticationTask().execute(null, null).get();
            QueryResult<Challenge> challenges = new SphereChallengeQueryTask(authResponse).execute().get();
            for (Challenge challenge : challenges.getResults()) {
                QueryResult<Product> products = new SphereProductQueryTask(authResponse, challenge.getId()).execute().get();
            }
        } catch (ExecutionException | InterruptedException e) {
            Log.e("riddle", "Could not fetch data from SPHERE.IO", e);
        }
        // TODO Send challenges info to view
        // TODO Send auth response to clicked challenge
        // TODO Do the query result in the corresponding place
        setContentView(R.layout.start);
    }

    public void startBreakfastChallenge(View view){
        Log.i("riddle", "start breakfast");

        ApplicationState.startNewGame();

        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }
}
