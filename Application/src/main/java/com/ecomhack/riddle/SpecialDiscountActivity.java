package com.ecomhack.riddle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecomhack.riddle.estimote.NearbyProductDiscovery;
import com.ecomhack.riddle.sound.SoundTask;

/**
 * Created by stephanwels1 on 09.05.15.
 */
public class SpecialDiscountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.special_discount);
    }

    public void backToHome(View view) {
        Log.i("riddle", "Used special discount");
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
