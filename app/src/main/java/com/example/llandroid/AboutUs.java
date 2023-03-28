package com.example.llandroid;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AboutUs extends AppCompatActivity {


    EditText t1, t2, t3;
    private AboutUsDBManager db2;
    private Button insertAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        t1 = (EditText) findViewById(R.id.edit_text_name);
        t2 = (EditText) findViewById(R.id.edit_text_email);
        t3 = (EditText) findViewById(R.id.edit_text_message);
        insertAboutUs = findViewById(R.id.submit_button);
        db2 = new AboutUsDBManager(AboutUs.this);

        /* saves the registration information the user created through the registration page in the database
               and gives them access log in into the app */
        insertAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = t1.getText().toString();
                String email = t2.getText().toString();
                String message = t3.getText().toString();


                if (fullName.isEmpty() && email.isEmpty() && message.isEmpty()) {
                    Toast.makeText(AboutUs.this, "Enter all data", Toast.LENGTH_LONG).show();
                }

                db2.AboutUsAddDBRecord(fullName, email, message);

                Toast.makeText(AboutUs.this, "About Us added", Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");


            }
        });
    }

}


