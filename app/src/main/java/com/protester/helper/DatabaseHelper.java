package com.protester.helper;

import com.firebase.client.Query;

public class DatabaseHelper extends FirebaseHelper {

    public DatabaseHelper(){
        super();
    }

    public Query retrieveStories(){
        Query queryRef = getFirebaseRef().child("/protest/").orderByKey();
        return queryRef;
    }
}
