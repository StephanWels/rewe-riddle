/*
package com.ecomhack.riddle.estimote;

import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.IBinder;

public class EstimoteService extends BluetoothClass.Service {
 @Override
 public IBinder onBind(Intent arg0) {
  return null;
 }
 
 @Override
 public int onStartCommand(Intent intent, int flags, int startId) {
  try {
   <b>EstimoteManager.Create((NotificationManager) this
     .getSystemService(Context.NOTIFICATION_SERVICE), this,
     intent);</b>
  } catch (Exception e) {
  }
  return START_STICKY;
 }
 
 @Override
 public void onDestroy() {
  super.onDestroy();
  <b>EstimoteManager.stop();</b>
 }
}
*/