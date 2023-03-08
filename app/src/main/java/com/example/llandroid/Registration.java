package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    // variables used in the Registration code
    TextView txLogIn;
    EditText t1,t2, t3,t4,t5;
    private RegistrationDBManager db2;
    private Button insertRegistration;

    @Override
    // sets up the view of the registration page, initializes methods, sets up attributes for the database manager
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        txLogIn = findViewById(R.id.txLogIn);

        t1=(EditText)findViewById(R.id.edtRegistrationFullName);
        t2=(EditText)findViewById(R.id.edtRegistrationEmail);
        t3=(EditText)findViewById(R.id.edtRegistrationMobile);
        t4=(EditText)findViewById(R.id.edtRegistrationPassword);
        t5=(EditText)findViewById(R.id.edtRegistrationConfirmPassword);
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


                if (fullName.isEmpty() && email.isEmpty() && mobile.isEmpty() && pass.isEmpty() && confirmPass.isEmpty()){
                    Toast.makeText(Registration.this, "Enter all data",Toast.LENGTH_LONG).show();
                    return;
                }

                db2.RegistrationAddDBRecord(fullName, email, mobile, pass,confirmPass);

                Toast.makeText(Registration.this, "Registration added",Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");


            }
        });
    }
}