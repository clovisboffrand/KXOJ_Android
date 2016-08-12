package com.radioserver.kxoj.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileSingleton {
    public static final int TIMEOUT = 30000;

    public static String PREFERENCES = "LeadsappConfig";

    private SharedPreferences mSettings;
    private SharedPreferences.Editor mEditor;

    private static UserProfileSingleton mInstance = null;

    private static final String AUTOMATIC = "AUTOMATIC";
    private static final String HOUR = "HOUR";
    private static final String MIN = "MIN";

    public static synchronized UserProfileSingleton getConfig(Context context) {
        if (mInstance == null) {
            mInstance = new UserProfileSingleton(context);
        }
        return mInstance;
    }

    private UserProfileSingleton(Context context) {
        mSettings = context.getSharedPreferences(PREFERENCES, 0);
        mEditor = mSettings.edit();
    }

    public boolean isAutomatic() {
        return mSettings.getBoolean(AUTOMATIC, false);
    }

    public void setAutomatic(boolean value) {
        mEditor.putBoolean(AUTOMATIC, value);
        mEditor.commit();
    }

    public int getHour() {
        return mSettings.getInt(HOUR, 0);
    }

    public void setHour(int value) {
        mEditor.putInt(HOUR, value);
        mEditor.commit();
    }

    public int getMin() {
        return mSettings.getInt(MIN, 0);
    }

    public void setMin(int value) {
        mEditor.putInt(MIN, value);
        mEditor.commit();
    }
}
