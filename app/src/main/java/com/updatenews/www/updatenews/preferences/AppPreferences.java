package com.updatenews.www.updatenews.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class AppPreferences {
    private SharedPreferences mPrefs;

    private static final String SHARED_PREFERENCE_NAME = "YLOGLITEFILE";

    public static final String TIME_FORMAT = "time_format";

    public static final String DATE_FORMAT = "date_format";
    public static final String PHONE_FORMAT = "phone_format";



    private AppPreferences(Context context) {
        mPrefs = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public static AppPreferences getAppPreferences(Context context) {
        return new AppPreferences(context);

    }

    public String getStringValue(String key, String defaultValue) {
        return mPrefs.getString(key, defaultValue);
    }

    public int getIntValue(String key) {
        return mPrefs.getInt(key, 0);
    }

    public int getIntValue(String key, int defaultValue) {
        return mPrefs.getInt(key, defaultValue);
    }

    public void putIntValue(String key, int value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public boolean getBooleanValue(String key) {
        return mPrefs.getBoolean(key, false);
    }

    public void putStringValue(String key, String value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putBooleanValue(String key, boolean value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putSetValues(String key, Set<String> value) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }


    public Set<String> getSetValue(String key) {
        return mPrefs.getStringSet(key, null);
    }


    public void putStringInPreferences(String[] key, String[] pairValues) {
        SharedPreferences.Editor editor = mPrefs.edit();
        for (int i = 0; i < key.length; i++) {
            editor.putString(key[i], pairValues[i]);
        }
        editor.commit();
    }

    public void removeFromPreferences(String key) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.remove(key);
        editor.commit();
    }

}
