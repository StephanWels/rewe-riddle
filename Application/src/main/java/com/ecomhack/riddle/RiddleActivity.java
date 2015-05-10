package com.ecomhack.riddle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ecomhack.riddle.sound.SoundTask;

public class RiddleActivity extends Activity {

    private String productToFind = null;

    TextView riddleHeaderText;
    TextView riddleDescriptionText;

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
    }

    public void checkWhetherCorrect(View view) {
        Log.i("riddle", "Am I right?");
        if (ApplicationState.getNearProducts().contains(productToFind) ) {   //&&
            Log.i("riddle", "YES!");
            if (ApplicationState.existsNextRiddle()) {
                ApplicationState.nextRiddle();
                Intent intent = new Intent(this, CorrectActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, WinActivity.class);
                startActivity(intent);
            }

        } else {
            Log.i("riddle", "NO!");
            new SoundTask(getApplicationContext(), R.raw.wrong).execute();
            ApplicationState.wrongAnswer();
            Log.i("riddle", ApplicationState.getNumberTriesLeft() + " tries left");
            if (ApplicationState.hasTriesLeft()) {
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
}
