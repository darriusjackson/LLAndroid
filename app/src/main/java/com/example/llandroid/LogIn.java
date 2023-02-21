package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;


public class LogIn extends AppCompatActivity {
    TextView txRegistration;
    EditText t1,t2;
    private LogInDBManager db;
    private Button insertLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txRegistration = findViewById(R.id.txRegistration);


        t1=(EditText)findViewById(R.id.edtRegistrationEmail);
        t2=(EditText)findViewById(R.id.edtRegistrationPassword);
        insertLogIn = findViewById(R.id.btnLogIn);
        db = new LogInDBManager(LogIn.this);

            insertLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick (View view) {
                String email = t1.getText().toString();
                String pass = t2.getText().toString();

                if (email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(LogIn.this, "Enter all data",Toast.LENGTH_LONG).show();
                    return;
                }

                db.LogInAddDBRecord(email,pass);

                Toast.makeText(LogIn.this, "Log In added",Toast.LENGTH_LONG).show();
                t1.setText("");
                t2.setText("");

         }
            });
    }
}