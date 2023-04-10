package csc481.linkedlist.llandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class TermsofService extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsofservice);
        TextView termsTextView = findViewById(R.id.terms_text_view);
        String termsString = getString(R.string.terms_of_service_description);
        termsTextView.setText(Html.fromHtml(termsString));
        Button acceptButton = findViewById(R.id.accept_button);
        //press the back button and it sends the user back to the registration page
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TermsofService.this, Registration.class));
                finish();
            }
        });
    }
}

