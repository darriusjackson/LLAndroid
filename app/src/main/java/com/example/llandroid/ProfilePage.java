package com.example.llandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfilePage extends AppCompatActivity {
    // variables used in the ProfilePage code
    EditText t1, t2, t3, t4,t5;
    private RegistrationDBManager db2;
    private Button insertUpdate;

    @Override
    // sets up the view of the profile page, initializes methods, sets up attributes for the database manager
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setupLogOutActivityLink();
        setupContactUsActivityLink();

        t1 = (EditText) findViewById(R.id.updRegistrationFullName);
        t2 = (EditText) findViewById(R.id.updRegistrationEmail);
        t3 = (EditText) findViewById(R.id.updRegistrationMobile);
        t4 = (EditText) findViewById(R.id.updRegistrationPassword);
        t5 = (EditText) findViewById(R.id.updRegistrationConfirmPassword);
        insertUpdate= findViewById(R.id.updRegistration);
        db2 = new RegistrationDBManager(ProfilePage.this);

        // variables used for the bottom navigation method
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_4);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // method used to get the bottom navigation to work
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        return true;
                    case R.id.calender:
                        startActivity(new Intent(ProfilePage.this,Calendar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notes:
                        startActivity(new Intent(ProfilePage.this,NotesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(ProfilePage.this,SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        /* saves the update  information the user created through the profile page in the database
               and gives them access log in into the app */
        insertUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = t1.getText().toString();
                String email = t2.getText().toString();
                String mobile = t3.getText().toString();
                String pass = t4.getText().toString();
                String confirmPass = t5.getText().toString();


                if (fullName.isEmpty() && email.isEmpty() && mobile.isEmpty() && pass.isEmpty() && confirmPass.isEmpty()) {
                    Toast.makeText(ProfilePage.this, "Enter all data", Toast.LENGTH_LONG).show();
                    return;
                }
                db2.RegistrationAddDBRecord(fullName, email, mobile, pass,confirmPass);

                Toast.makeText(ProfilePage.this, "Update added", Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");



            }
        });
    }

    // sets up the log out link the user can click on if they want to log out the app
    private void setupLogOutActivityLink() {
        TextView LogOutLink = findViewById(R.id.logOut);
        LogOutLink.setTextColor(Color.BLACK);
        LogOutLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logOutIntent = new Intent(ProfilePage.this, LogIn.class);
                startActivity(logOutIntent);
            }
        });
    }
    // sets up the contact us link the user can click on if they want to leave any thoughts about the app
    private void setupContactUsActivityLink() {
        TextView contactUsLink = findViewById(R.id.contactUs);
        contactUsLink.setTextColor(Color.BLACK);
       contactUsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactUsIntent = new Intent(ProfilePage.this, AboutUs.class);
                startActivity(contactUsIntent);
            }
        });
    }
}