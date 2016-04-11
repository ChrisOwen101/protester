package com.protester.helper;

import com.firebase.client.Firebase;

public abstract class FirebaseHelper {

    private static Firebase firebaseRef;

    public FirebaseHelper(){
        initialiseFirebase();
    }

    public static void initialiseFirebase(){
        firebaseRef = new Firebase("https://protester.firebaseio.com");
    }

    public static Firebase getFirebaseRef(){
        return firebaseRef;
    }
}
