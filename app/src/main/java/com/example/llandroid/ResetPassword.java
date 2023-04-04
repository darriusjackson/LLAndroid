package com.example.llandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPassword extends AppCompatActivity {

    EditText password, resetPass;
    TextView email;
    Button reset;

    LogInDBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        email = (TextView) findViewById(R.id.email_reset);
        password = (EditText) findViewById(R.id.pass_forgot);
        resetPass = (EditText) findViewById(R.id.newPass_forgot);
        reset = findViewById(R.id.btnForgotPass);
        db = new LogInDBManager(ResetPassword.this);

        // displays the user's email on the page
        Intent intent2 = getIntent();
        email.setText(intent2.getStringExtra("email"));

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            // method used to update the password of the user
            public void onClick (View view) {
                String emailAddress = email.getText().toString();
                String pass = password.getText().toString();
                String repass = resetPass.getText().toString();
                Boolean updPass = db.updatePassword(emailAddress,pass);



                if (pass.equals(repass)) {

                    if (updPass == true) {
                        Intent intent = new Intent(getApplicationContext(), LogIn.class);
                        startActivity(intent);
                        Toast.makeText(ResetPassword.this, "Password Updated!", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ResetPassword.this, "Password Not Updated", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ResetPassword.this, "Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }

