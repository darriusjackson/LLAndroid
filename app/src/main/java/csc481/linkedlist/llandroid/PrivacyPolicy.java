package csc481.linkedlist.llandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacypolicy);
        TextView privacyTextView = findViewById(R.id.privacy_text_view);
        String privacyString = getString(R.string.privacy_policy_description);
        privacyTextView.setText(Html.fromHtml(privacyString));
        Button backButton = findViewById(R.id.backprivacy_button);
        //press the back button and it sends the user back to the registration page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrivacyPolicy.this, Registration.class));
                finish();
            }
        });
    }
}

