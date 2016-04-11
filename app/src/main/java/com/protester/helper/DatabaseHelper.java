package com.protester.helper;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Query;

public class DatabaseHelper extends FirebaseHelper {

    public DatabaseHelper(){
        super();
    }

    public Query retrieveStories(ChildEventListener childEventListener){
        Query queryRef = getFirebaseRef().child("/story/").orderByKey();
        queryRef.addChildEventListener(childEventListener);
        return queryRef;
    }
}
