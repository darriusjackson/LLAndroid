package csc481.linkedlist.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;



public class Registration extends AppCompatActivity {
    // variables used in the Registration code
    TextView txLogIn;
    EditText t1, t2, t3, t4, t5;
    private RegistrationDBManager db2;
    private Button insertRegistration;

    @Override
    // sets up the view of the registration page, initializes methods, sets up attributes for the database manager
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txLogIn = findViewById(R.id.txLogIn);
        setupLogInActivityLink();

        CheckBox TOSbox = findViewById(R.id.boxTOS);
        TextView termsofService = findViewById(R.id.link_textview);
        CheckBox PPbox = findViewById(R.id.boxPP);
        TextView privacyPolicy = findViewById(R.id.link_PP);
        t1 = (EditText) findViewById(R.id.edtRegistrationFullName);
        t2 = (EditText) findViewById(R.id.edtRegistrationEmail);
        t3 = (EditText) findViewById(R.id.edtRegistrationMobile);
        t4 = (EditText) findViewById(R.id.edtRegistrationPassword);
        t5 = (EditText) findViewById(R.id.edtRegistrationConfirmPassword);
        insertRegistration = findViewById(R.id.btnRegistration);
        db2 = new RegistrationDBManager(Registration.this);

        /* saves the registration information the user created through the registration page in the database
               and gives them access log in into the app */
        insertRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = t1.getText().toString();
                String email = t2.getText().toString();
                String mobile = t3.getText().toString();
                String pass = t4.getText().toString();
                String confirmPass = t5.getText().toString();


                if (fullName.isEmpty() && email.isEmpty() && mobile.isEmpty() && pass.isEmpty() && confirmPass.isEmpty()) {
                    Toast.makeText(Registration.this, "Enter all data", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    startActivity(new Intent(Registration.this, Calendar.class));
                }

                db2.RegistrationAddDBRecord(fullName, email, mobile, pass, confirmPass);

                Toast.makeText(Registration.this, "Registration added", Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");


            }
        });

        // method used so the user can click the link to view the terms of service
        termsofService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, TermsofService.class);
                startActivity(intent);
            }
        });
        // disables the check box initially and makes the button unusable
        insertRegistration.setEnabled(false);
        //method used so the user can check the box
        TOSbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                insertRegistration.setEnabled(isChecked);
            }
        });
        // method used so the user can click the link to view the privacy policy
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });

        // disables the check box initially and makes the button unusable
        insertRegistration.setEnabled(false);
        //method used so the user can check the box
        PPbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                insertRegistration.setEnabled(isChecked);
            }
        });
    }





    // sets up the log in link the user can click on if they do have an account
    private void setupLogInActivityLink() {
        TextView LogInLink = findViewById(R.id.txLogIn);
        LogInLink.setTextColor(Color.BLACK);
        LogInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logInIntent = new Intent(Registration.this, LogIn.class);
                startActivity(logInIntent);
            }
        });
    }

}