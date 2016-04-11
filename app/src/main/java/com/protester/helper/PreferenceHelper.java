package com.protester.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.protester.application.ProtesterApplication;

public class PreferenceHelper {
    private static final String SHARED_PREFERENCES_KEY = "com.guardian_preferences";

    public static final String SIGNED_IN = "signed_in";
    private static final String USER_NAME = "username";
    private static final String USER_EMAIL = "email";
    private static final String AUTH_TOKEN = "auth_token";
    private static final String UNIQUE_ID = "unique_id";

    private static PreferenceHelper preferenceHelper;
    private Context context;
    private SharedPreferences sharedPreferences;

    public static PreferenceHelper get() {
        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper();
        }
        return preferenceHelper;
    }

    private PreferenceHelper() {
        context = ProtesterApplication.getAppContext();
        sharedPreferences = ProtesterApplication.getAppContext().getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return sharedPreferences;
    }

    public void setUserIsSignedIn(){
        writeBoolean(SIGNED_IN, true);
    }

    public void setUserIsSignedOut(){
        writeBoolean(SIGNED_IN, false);
    }

    public boolean isUserSignedIn(){
        return sharedPreferences.getBoolean(SIGNED_IN, false);
    }

    public void setUserEmail(String email){
        writeString(USER_EMAIL, email);
    }

    public String getUserEmail(){
        return sharedPreferences.getString(USER_EMAIL, null);
    }

    public void setUsername(String name){
        writeString(USER_NAME, name);
    }

    public String getUserName(){
        return sharedPreferences.getString(USER_NAME, null);
    }

    public void setAuthToken(String name){
        writeString(AUTH_TOKEN, name);
    }

    public String getAuthToken(){
        return sharedPreferences.getString(AUTH_TOKEN, null);
    }

    public void setUniqueID(String name){
        writeString(UNIQUE_ID, name);
    }

    public String getUniqueID(){
        return sharedPreferences.getString(UNIQUE_ID, null);
    }

    private void clearString(String key) {
        sharedPreferences.edit().putString(key, null).apply();
    }

    private void writeBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private void writeString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    private void writeInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    private void writeLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }
}
