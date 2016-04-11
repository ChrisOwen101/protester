package com.protester.eventbus;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class EventBus {
    private static Bus bus;

    private EventBus() {
        // Cannot instantiate
    }

    public static Bus getBus() {
        if (bus == null)
            bus = new Bus(ThreadEnforcer.ANY);
        return bus;
    }

    public static void post(@NonNull Object event) {
        try {
            getBus().post(event);
        } catch (RuntimeException e) {
            Log.e("EventBus", "EventBus", e);
        }
    }

    public static boolean register(@Nullable Object o) {
        if (o == null)
            return false;

        // Register will throw an IllegalArgumentException if the object being registered doesn't
        // implement the appropriate listeners. This could be handled in a try / catch block, but it
        // would swallow the exceptions, which could be useful to the development team.
        getBus().register(o);

        return true;
    }

    public static boolean unregister(@Nullable Object o) {
        if (o == null)
            return false;
        try {
            getBus().unregister(o);
        } catch (IllegalArgumentException ex) {
            Log.e("EventBus", "EventBus", ex);

            return false;
        }

        return true;
    }
}

