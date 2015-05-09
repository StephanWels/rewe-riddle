package com.ecomhack.riddle.estimote;

import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ecomhack.riddle.ApplicationState;
import com.ecomhack.riddle.SphereActivity;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.example.android.basicnotifications.R;



public class EstimoteManager {

    private static final int NOTIFICATION_ID = 123;
    private static BeaconManager beaconManager;
    private static NotificationManager notificationManager;
    private static final int REGISTRATION_REGION_MAJOR=25140;
    private static final Region REGISTRATION_REGION = new Region("registration_region", null, REGISTRATION_REGION_MAJOR, null);
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("all_beacons", null, null, null);
    private  final NearbyProductDiscovery nearbyProductDiscovery;

    private static Context currentContext;

    // Create everything we need to monitor the beacons
    public EstimoteManager(NotificationManager notificationMngr,
                              Context context, final Intent i) {
        notificationManager = notificationMngr;
        currentContext = context;
        nearbyProductDiscovery = new NearbyProductDiscovery(currentContext);
        try {

            // Create a beacon manager
            beaconManager = new BeaconManager(currentContext);

            // We want the beacons heartbeat to be set at one second.
            beaconManager.setBackgroundScanPeriod(TimeUnit.SECONDS.toMillis(1),
                    0);

            // Method called when a beacon gets...
            beaconManager.setMonitoringListener(new MonitoringListener() {
                // ... close to us.
                @Override
                public void onEnteredRegion(Region region, List<Beacon> beacons) {
                    Log.i("riddle", "found registration beacon.");
                    if (!ApplicationState.isGameActive()){
                        postNotificationIntent("Play with us!",
                                "Tap to start the REWE RIDDLE", i);
                    }
                }

                // ... far away from us.
                @Override
                public void onExitedRegion(Region region) {
                    // NOOP
                }
            });

            beaconManager.setRangingListener(new BeaconManager.RangingListener() {
                @Override
                public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                    nearbyProductDiscovery.discoverProducts(list);
                }
            });

            // Connect to the beacon manager...
            beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                @Override
                public void onServiceReady() {
                    try {
                        // ... and start the monitoring
                        beaconManager.startMonitoring(REGISTRATION_REGION);
                        beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    // Pops a notification in the task bar
    public void postNotificationIntent(String title, String msg, Intent i) {
        final Intent intent = new Intent(currentContext, SphereActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentContext, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(currentContext)
            .setSmallIcon(R.drawable.ic_stat_notification)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setLargeIcon(BitmapFactory.decodeResource(currentContext.getResources(), R.drawable.ic_launcher))
            .setContentTitle("Play with us!")
            .setContentText("Tap to start the REWE RIDDLE")
            .setSubText("").build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_LIGHTS;
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

    // Stop beacons monitoring, and closes the service
    public void stop() {
        Log.i("riddle", "Stopping estimote manager.");
        try {
            if (nearbyProductDiscovery != null){
                nearbyProductDiscovery.close();
            }
            beaconManager.stopMonitoring(REGISTRATION_REGION);
            beaconManager.disconnect();
        } catch (Exception e) {
            Log.e("riddle","Error during Estimote manager shutdown.", e);
        }
    }
}
