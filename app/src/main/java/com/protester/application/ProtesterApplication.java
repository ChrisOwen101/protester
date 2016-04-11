package com.protester.application;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

public class ProtesterApplication extends Application {

    public static ProtesterApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;

        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }

    public static Context getAppContext(){
        return appContext;
    }
}
