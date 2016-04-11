package com.protester.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.newschat.newschat.R;

public class FragmentHelper {

    public static void replaceFragment(FragmentActivity activity, Fragment f){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f, f.getClass().getName())
                .addToBackStack(f.getClass().getName())
                .commit();
    }

    public static void replaceFragmentNoBackStack(FragmentActivity activity, Fragment f){
        replaceFragmentNoBackStack(activity, f, R.id.container);
    }

    public static void replaceFragmentNoBackStack(FragmentActivity activity, Fragment f, int containerRes){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerRes, f, f.getClass().getName())
                .commit();
    }
}
