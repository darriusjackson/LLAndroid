package csc481.linkedlist.llandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


public class LogIn extends AppCompatActivity {
    // variables used in the LogIn code
    TextView txRegistration;
    EditText t1,t2;
    private LogInDBManager db;
    private Button insertLogIn;

    @Override
    // sets up the view of the login page, initializes methods, sets up attributes for the database manager
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txRegistration = findViewById(R.id.txRegistration);
        setupRegisterActivityLink();
        setupForgetPasswordLink();
        t1=(EditText)findViewById(R.id.edtRegistrationEmail);
        t2=(EditText)findViewById(R.id.edtRegistrationPassword);
        insertLogIn = findViewById(R.id.btnLogIn);
        db = new LogInDBManager(LogIn.this);


            insertLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            /* saves the login information the user created through the registration page in the database
               and gives them access into the app */
          public void onClick (View view) {
                String email = t1.getText().toString();
                String pass = t2.getText().toString();

                if (email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(LogIn.this, "Enter all data",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    startActivity(new Intent(LogIn.this, Calendar.class));
                }

                db.LogInAddDBRecord(email,pass);

                Toast.makeText(LogIn.this, "Log In added",Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");

         }
            });


            }

            // sets up the registration link the user can click on if they do not have an account
    private void setupRegisterActivityLink() {
        TextView registerLink = findViewById(R.id.txRegistration);
        registerLink.setTextColor(Color.BLACK);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LogIn.this, Registration.class);
                startActivity(registerIntent);
            }
        });

        }
    // sets up the forgot password link the user can click on if they forgot their password
    private void setupForgetPasswordLink() {
        TextView forgotLink = findViewById(R.id.txForgotPassword);
        forgotLink.setTextColor(Color.BLACK);
        forgotLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotIntent = new Intent(LogIn.this, ForgotPassword.class);
                startActivity(forgotIntent);
            }
        });

    }

    }


