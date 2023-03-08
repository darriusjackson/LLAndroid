package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    // variable to used to delay the runtime of the app when it first opens
    Handler h = new Handler();

    @Override
    // method used to run the app when the user taps on the app icon
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,SplashScreen.class);
                startActivity(i);
                finish();

            }
        }, 3000);
    }
}