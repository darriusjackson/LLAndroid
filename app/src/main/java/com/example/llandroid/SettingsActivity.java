package com.example.llandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity
{
    // declaring variables for the settings view, switch, text views, and user settings
    private View parentView;
    private SwitchMaterial themeSwitch;
    private TextView themeTV, titleTV, aboutID, versionID;

    private UserSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // gets the UserSettings Java file
        settings = (UserSettings) getApplication();

        // gets the methods shown below
        initWidgets();
        loadSharedPreferences();
        initSwitchListener();
    }

    // set the text view variables to their respective IDs
    private void initWidgets()
    {
        themeTV = findViewById(R.id.themeTV);
        titleTV = findViewById(R.id.titleTV);
        themeSwitch = findViewById(R.id.themeSwitch);
        parentView = findViewById(R.id.parentView);
        aboutID = findViewById(R.id.aboutID);
        versionID = findViewById(R.id.versionID);
    }

    //sets up the theme of the app based on the users preference
    private void loadSharedPreferences()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE);
        String theme = sharedPreferences.getString(UserSettings.CUSTOM_THEME, UserSettings.LIGHT_THEME);
        settings.setCustomTheme(theme);
        updateView();
    }

    // sets up the switch for the theme
    private void initSwitchListener()
    {
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked)
            {
                if(checked)
                    settings.setCustomTheme(UserSettings.DARK_THEME);
                else
                    settings.setCustomTheme(UserSettings.LIGHT_THEME);

                SharedPreferences.Editor editor = getSharedPreferences(UserSettings.PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(UserSettings.CUSTOM_THEME, settings.getCustomTheme());
                editor.apply();
                updateView();
            }
        });
    }

    // updates the color of the text based on the color theme the user picks
    private void updateView()
    {
        final int black = ContextCompat.getColor(this, R.color.black);
        final int white = ContextCompat.getColor(this, R.color.white);

        if(settings.getCustomTheme().equals(UserSettings.DARK_THEME))
        {
            titleTV.setTextColor(white);
            aboutID.setTextColor(white);
            versionID.setTextColor(white);
            themeTV.setTextColor(white);
            themeTV.setText("Dark");
            parentView.setBackgroundColor(black);
            themeSwitch.setChecked(true);
        }
        else
        {
            aboutID.setTextColor(black);
            versionID.setTextColor(black);
            titleTV.setTextColor(black);
            themeTV.setTextColor(black);
            themeTV.setText("Light");
            parentView.setBackgroundColor(white);
            themeSwitch.setChecked(false);
        }

    }

}