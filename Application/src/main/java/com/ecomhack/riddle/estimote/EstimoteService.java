package com.ecomhack.riddle.estimote;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class EstimoteService extends Service {
    private EstimoteManager estimoteManager = null;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("riddle", "Starting estimote service.");
        try {
            estimoteManager = new EstimoteManager((NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE), this, intent);
        } catch (Exception e) {
            Log.e("riddle","Error during Estimote startup.", e);
        }
        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("riddle", "Unbinding estimote service.");
        estimoteManager.stop();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("riddle", "Destroying estimote service.");
        super.onDestroy();
        estimoteManager.stop();
    }
}