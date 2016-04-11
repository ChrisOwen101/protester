package com.protester.helper;

import android.app.Activity;
import android.widget.Toast;

public class ToastHelper {

    public static void showToast(Activity activity, String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }
}
