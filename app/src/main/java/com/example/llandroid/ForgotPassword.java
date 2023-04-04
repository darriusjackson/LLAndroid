package com.example.llandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button reset;

    LogInDBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        email=(EditText)findViewById(R.id.email_forgot);
        reset = findViewById(R.id.btnForgot);
        db = new LogInDBManager(ForgotPassword.this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            // method used to check if the user's email is valid before resetting the password
            public void onClick (View view) {
                String emailAddress = email.getText().toString();
                Boolean checkEmailAddress = db.checkEmail(emailAddress);

                if (checkEmailAddress==true)
                {
                    Intent intent = new Intent(getApplicationContext(),ResetPassword.class);
                    intent.putExtra("email", emailAddress);
                    startActivity(intent);

                } else {
                    Toast.makeText(ForgotPassword.this, "Email does not exist",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

