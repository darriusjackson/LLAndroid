package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class Registration extends AppCompatActivity {
    TextView txLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txLogIn = findViewById(R.id.txLogIn);
        txLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}