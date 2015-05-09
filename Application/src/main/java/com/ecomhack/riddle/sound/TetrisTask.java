package com.ecomhack.riddle.sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import com.ecomhack.riddle.R;

import java.net.URL;

public class TetrisTask extends AsyncTask<URL, Integer, Long> {

    private static final String TAG = "TetrisTask";

    private Context applicationContext;

    public TetrisTask(Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    protected Long doInBackground(URL... urls) {
        MediaPlayer mediaPlayer = MediaPlayer.create(applicationContext, R.raw.tetris);
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
