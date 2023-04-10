package csc481.linkedlist.llandroid;

import android.app.Application;

public class UserSettings extends Application {
    // declaring string variables for the preferences and themes
    public static final String PREFERENCES = "preferences";

    public static final String CUSTOM_THEME = "customTheme";
    public static final String LIGHT_THEME = "lightTheme";
    public static final String DARK_THEME = "darkTheme";

    private String customTheme;

    // getting and setting the custom theme the user will state with the switch
    public String getCustomTheme() {
        return customTheme;
    }

    public void setCustomTheme(String customTheme) {
        this.customTheme = customTheme;
    }
}