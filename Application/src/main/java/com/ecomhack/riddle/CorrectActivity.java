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

import com.ecomhack.riddle.imagedownloader.DownloadImageTask;
import com.ecomhack.riddle.R;

import java.net.URL;

public class CorrectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_correct, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        setPicture(null);
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
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        new DownloadImageTask(imageView)
                .execute("https://d9gkwhfwam31p.cloudfront.net/8750254/1326950_lightbox.png");


    }

    public void nextRiddle(View view) {
        Log.i("riddle", "next riddle");
        Intent intent = new Intent(this, RiddleActivity.class);
        startActivity(intent);
    }

}
