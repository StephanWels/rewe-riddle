package com.ecomhack.riddle.sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.ecomhack.riddle.R;

import java.net.URL;

public class SoundTask extends AsyncTask<URL, Integer, Long> {

    private static final String TAG = "TetrisTask";

    private Context applicationContext;
    private int resource;

    public SoundTask(Context applicationContext, int ressource) {
        this.applicationContext = applicationContext;
    }


    protected Long doInBackground(URL... urls) {
        MediaPlayer mediaPlayer = MediaPlayer.create(applicationContext, resource);
        mediaPlayer.start();

        return 0L;
    }

    protected void onProgressUpdate(Integer... progress) {
        Log.v(TAG, "onProgressUpdate");
    }

    protected void onPostExecute(Long result) {
        Log.v(TAG, "onPostExecute");
    }

}
