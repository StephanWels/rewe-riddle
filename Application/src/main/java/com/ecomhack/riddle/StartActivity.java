package com.ecomhack.riddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.basicnotifications.R;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class StartActivity extends Activity {

        private static final String TAG = "start";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.start);
        }
        public void startBreakfastChallenge(View view){
            Log.i("riddle","start breakfast");
            Intent intent = new Intent(this, RiddleActivity.class);
            startActivity(intent);
        }
}
