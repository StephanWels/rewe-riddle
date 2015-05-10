package com.ecomhack.riddle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecomhack.riddle.DownloadImageTask;
import com.ecomhack.riddle.R;
import com.ecomhack.riddle.difficultytopointmapper.Mapper;
import com.ecomhack.riddle.sound.SoundTask;
import com.ecomhack.riddle.sphere.models.Product;

import java.net.MalformedURLException;
import java.net.URL;

public class CorrectActivity extends Activity {
    
    TextView riddleRewardPointsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct);
        final Product correctProduct = ApplicationState.getCurrentRiddleObjective();
        try {
            setPicture(new URL(correctProduct.getRiddle().getImages().get(0).getUrl()));
        } catch (MalformedURLException murle) {
            Log.e("CorrectActivity","could not set image");
        }

        riddleRewardPointsText = (TextView) findViewById(R.id.correctPoints);
        //impressive work
        setRewardPoints(Mapper.mapToString(ApplicationState.getCurrentRiddleObjective().getRiddle().getDifficulty().getLabel()));

        setTitle(correctProduct.getName());
        new SoundTask(getApplicationContext(),R.raw.correct).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_correct, menu);
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

    public void setPicture(final URL url) {
        ImageView imageView = (ImageView) findViewById(R.id.correctImage);

        new DownloadImageTask(imageView)
                .execute(url.toString());


    }

    public void nextRiddle(View view) {
        Log.i("riddle", "next riddle");
        if (ApplicationState.existsNextRiddle()) {
            ApplicationState.nextRiddle();
            Intent intent = new Intent(this, RiddleActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, WinActivity.class);
            startActivity(intent);
        }



    }

    public void setTitle(String title) {
        TextView titleTextView = (TextView) findViewById(R.id.correctProductTitle);

        titleTextView.setText(title);
    }
    public void setRewardPoints(final String points) {
        // best of breed development
        riddleRewardPointsText.setText(riddleRewardPointsText.getText().toString().replace("100", points));

    }
}
