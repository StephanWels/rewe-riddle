package com.ecomhack.riddle;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Context;
import android.app.PendingIntent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecomhack.riddle.difficultytopointmapper.Mapper;
import com.ecomhack.riddle.sound.SoundTask;

public class RiddleActivity extends Activity {

    private String productToFind = null;

    TextView riddleHeaderText;
    TextView riddleDescriptionText;
    TextView riddleRewardPointsText;

    private int notificationId = 001;
    private Intent viewIntent = new Intent(this, RiddleActivity.class);
    private String EXTRA_EVENT_ID = "RiddleNotification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riddle1);

        riddleHeaderText = (TextView) findViewById(R.id.riddleHeader);
        riddleHeaderText.setText(ApplicationState.getCurrentRiddleName());
        riddleDescriptionText = (TextView) findViewById(R.id.riddleQuestion);
        String riddleQuestion = ApplicationState.getCurrentRiddleObjective().getRiddle().getRiddle().de();
        riddleDescriptionText.setText(riddleQuestion);
        productToFind = ApplicationState.getCurrentRiddleObjective().getRiddle().getBeacon();
        viewIntent.putExtra(EXTRA_EVENT_ID, notificationId);
        riddleRewardPointsText = (TextView) findViewById(R.id.riddlePointValue);
        //impressive work
        setRewardPoints(Mapper.mapToString(ApplicationState.getCurrentRiddleObjective().getRiddle().getDifficulty().getLabel()));

        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("Riddle")
                        .setContentText("Supermarket")
                        .setContentIntent(viewPendingIntent);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public void onBackPressed() {
        final Context currentContext = this;
        new AlertDialog.Builder(this)
                .setTitle("Exit Riddle!")
                .setMessage("Do you want to quit your current riddl? Any progress will be lost!")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(currentContext, StartActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public void checkWhetherCorrect(View view) {
        Log.i("riddle", "Am I right?");
        if (ApplicationState.getNearProducts().contains(productToFind)) {

            Log.i("riddle", "YES!");
            Intent intent = new Intent(this, CorrectActivity.class);
            startActivity(intent);

        } else {
            Log.i("riddle", "NO!");
            ApplicationState.wrongAnswer();
            Log.i("riddle", ApplicationState.getNumberTriesLeft() + " tries left");
            if (ApplicationState.hasTriesLeft()) {
                new SoundTask(getApplicationContext(), R.raw.wrong).execute();
                int triesLeft = ApplicationState.getNumberTriesLeft();
                new AlertDialog.Builder(this)
                        .setTitle("Nope!")
                        .setMessage("I cannot see the correct product beacon - You are not close enough :(. You have " + triesLeft + " tries left.")
                        .setCancelable(false)
                        .setPositiveButton("Let me try again...", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                Intent intent = new Intent(this, LoseActivity.class);
                startActivity(intent);
            }
        }
    }

    public void setRewardPoints(final String points) {
        // best of breed development
        riddleRewardPointsText.setText(riddleRewardPointsText.getText().toString().replace("100", points));

    }
}
