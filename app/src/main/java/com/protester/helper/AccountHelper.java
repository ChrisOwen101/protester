package com.protester.helper;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.newschat.newschat.pojo.UserAccount;

public class AccountHelper extends FirebaseHelper{

    public AccountHelper(){
        super();
    }

    public void createAccount(final String email, final String password, final String name, final Firebase.AuthResultHandler handler){
        getFirebaseRef().createUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                loginUser(email, password, name, handler);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                handler.onAuthenticationError(firebaseError);
            }
        });
    }

    public void loginUser(final String email, String password, final String name, final Firebase.AuthResultHandler handler){
        getFirebaseRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                createAccountReference(authData.getUid(), name);
                saveToSharedPrefs(email, authData);


                handler.onAuthenticated(authData);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                PreferenceHelper.get().setUserIsSignedOut();
                handler.onAuthenticationError(firebaseError);
            }
        });
    }

    public void loginUser(final String email, String password, final Firebase.AuthResultHandler handler){
        getFirebaseRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                saveToSharedPrefs(email, authData);
                handler.onAuthenticated(authData);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                PreferenceHelper.get().setUserIsSignedOut();
                handler.onAuthenticationError(firebaseError);
            }
        });
    }

    public Query getUserDetails(String uuid, ValueEventListener v){
        Query queryRef = getFirebaseRef().child("/users/" + uuid);
        queryRef.addListenerForSingleValueEvent(v);
        return queryRef;
    }

    private void saveToSharedPrefs(String email, AuthData data){
        PreferenceHelper.get().setUserIsSignedIn();
        PreferenceHelper.get().setUserEmail(email);
        PreferenceHelper.get().setAuthToken(data.getToken());
        PreferenceHelper.get().setUniqueID(data.getUid());

        getUserDetails(data.getUid(), new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PreferenceHelper.get().setUsername((String)dataSnapshot.child("username").getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public static void logout(){
        PreferenceHelper.get().setUserIsSignedOut();
        PreferenceHelper.get().setUserEmail("");
        PreferenceHelper.get().setAuthToken("");
        PreferenceHelper.get().setUniqueID("");
        PreferenceHelper.get().setUsername("");
    }

    public void createAccountReference(String uuid, String name){
        getFirebaseRef().child("/users/" + uuid).setValue(new UserAccount(name));
    }
}
