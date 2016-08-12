package com.radioserver.kxoj.screens;

import com.radioserver.kxoj.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getBaseContext(), Main.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}