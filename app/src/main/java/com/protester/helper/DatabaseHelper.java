package com.protester.helper;

import com.firebase.client.Query;
import com.protester.pojo.Protest;

public class DatabaseHelper extends FirebaseHelper {

    public DatabaseHelper(){
        super();
    }

    public Query retrieveProtests(){
        Query queryRef = getFirebaseRef().child("/protest/").orderByKey();
        return queryRef;
    }

    public void addProtest(){
        Protest group = new Protest("Name", "Chris", "Location", "www.google.com", 100, 200);
        getFirebaseRef().child("/protest/" + group.getID()).setValue(group);

    }
}
