//
//  SplashScreenActivity.java
//  com.hp.linkreadersdk
//  LinkReaderSDK
//
//  Copyright (c) 2015 HP. All rights reserved.

package com.hp.linkreadersdksample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class SplashScreenActivity extends Activity {

    public static final String SHOULD_SHOW_SPLASHSCREEN = "SHOULD_SHOW_SPLASHSCREEN";

    private Handler nextActivityHandler;

    private Runnable goToNextActivityRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.hp.linkreadersdksample.R.layout.activity_splashscreen);
        nextActivityHandler = new Handler();

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        boolean shouldShow = PreferenceManager.
                getDefaultSharedPreferences(this).getBoolean(SHOULD_SHOW_SPLASHSCREEN, true);
        if (!shouldShow) {
            /*Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();*/
            Intent intent = new Intent(this,UserActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
            return;
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences
                .edit()
                .putBoolean(SHOULD_SHOW_SPLASHSCREEN, false)
                .apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nextActivityHandler.removeCallbacks(goToNextActivityRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nextActivityHandler.postDelayed(goToNextActivityRunnable, 3000);
    }
}