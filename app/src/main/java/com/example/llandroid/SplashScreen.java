package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// displays the splash screen of the app when the user first taps on the app icon
public class SplashScreen extends AppCompatActivity {

    Handler h = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this,Registration.class);
                startActivity(i);
                finish();

            }
        }, 3000);
    }
}