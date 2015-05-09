package com.ecomhack.riddle.sphere;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ecomhack.riddle.ConstantsStateKeys;
import com.ecomhack.riddle.models.AuthResponse;
import com.ecomhack.riddle.models.Challenge;
import com.ecomhack.riddle.models.QueryResult;
import com.example.android.basicnotifications.R;

import java.util.concurrent.ExecutionException;

public class SphereActivity extends Activity {

    private static final String TAG = "SphereActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sphere);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sphere, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void callSphere(View view) throws ExecutionException, InterruptedException {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        int score =  pref.getInt(ConstantsStateKeys.KEY_SCORE, 0);
        Log.i("riddle", "Score: " + score);
        editor.putInt(ConstantsStateKeys.KEY_SCORE, score + 100);
        editor.commit(); // commit changes

        AuthResponse authResponse = new SphereAuthenticationTask().execute(null, null).get();
        QueryResult<QueryResult<Challenge>> challenges = new SphereChallengeQueryTask().execute(authResponse).get();
    }
}
