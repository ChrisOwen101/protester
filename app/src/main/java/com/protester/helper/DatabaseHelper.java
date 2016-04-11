package com.protester.helper;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.newschat.newschat.pojo.ChatMessage;
import com.newschat.newschat.pojo.ImageTopic;
import com.newschat.newschat.pojo.QuoteTopic;
import com.newschat.newschat.pojo.StoryGroup;
import com.newschat.newschat.pojo.Topic;

import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends FirebaseHelper {

    public DatabaseHelper(){
        super();
    }

    public void addStory(String headline, String url){
        StoryGroup group = new StoryGroup(headline, url);
        getFirebaseRef().child("/story/" + group.getID()).setValue(group);
    }

    public void addStory(String headline, String url, String imageUrl){
        StoryGroup group = new StoryGroup(headline, url, imageUrl);
        getFirebaseRef().child("/story/" + group.getID()).setValue(group);

        addTopic(group.getID(), "General");
    }

    public void addTopic(int storyID, String subject){
        Topic topic = new Topic(storyID, subject);
        getFirebaseRef().child("/story/" + storyID + "/topics/").push().setValue(topic.getID());
        getFirebaseRef().child("/topic/" + topic.getID()).setValue(topic);
    }

    public void addQuoteTopic(int storyID, String subject){
        QuoteTopic topic = new QuoteTopic(storyID, cleanUpQuote(subject));
        getFirebaseRef().child("/story/" + storyID + "/topics/").push().setValue(topic.getID());
        getFirebaseRef().child("/topic/" + topic.getID()).setValue(topic);
    }

    private String cleanUpQuote(String quote){
        if(quote.charAt(0) == '"'){
            quote = quote.substring(1);
        }

        if(quote.charAt(quote.length()-1) == '"'){
            quote = quote.substring(0, quote.length()-1);
        }

        return quote;
    }

    public void addImageTopic(int storyID, String subject){
        ImageTopic topic = new ImageTopic(storyID, subject);
        getFirebaseRef().child("/story/" + storyID + "/topics/").push().setValue(topic.getID());
        getFirebaseRef().child("/topic/" + topic.getID()).setValue(topic);
    }

    public void sendMessage(String message, int topicID){
        ChatMessage chatMessage = new ChatMessage(message);
        getFirebaseRef().child("/topic/" + topicID + "/messages/").push().setValue(chatMessage);
    }

    public void increaseStoryRoomNumber(final int storyID){
        getFirebaseRef().child("/story/" + storyID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long number = (long) dataSnapshot.child("numberOfUsers").getValue();

                Map<String, Object> nickname = new HashMap<String, Object>();
                nickname.put("numberOfUsers", number+1);
                getFirebaseRef().child("/story/" + storyID).updateChildren(nickname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void decreaseStoryRoomNumber(final int storyID){
        getFirebaseRef().child("/story/" + storyID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long number = (long) dataSnapshot.child("numberOfUsers").getValue();

                Map<String, Object> nickname = new HashMap<String, Object>();
                nickname.put("numberOfUsers", number-1);
                getFirebaseRef().child("/story/" + storyID).updateChildren(nickname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void increaseTopicRoomNumber(final int topicID){
        getFirebaseRef().child("/topic/" + topicID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long number = (long) dataSnapshot.child("numberOfUsers").getValue();

                Map<String, Object> nickname = new HashMap<String, Object>();
                nickname.put("numberOfUsers", number+1);
                getFirebaseRef().child("/topic/" + topicID).updateChildren(nickname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void decreaseTopicRoomNumber(final int topicID){
        getFirebaseRef().child("/topic/" + topicID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long number = (long) dataSnapshot.child("numberOfUsers").getValue();

                Map<String, Object> nickname = new HashMap<String, Object>();
                nickname.put("numberOfUsers", number-1);
                getFirebaseRef().child("/topic/" + topicID).updateChildren(nickname);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public Query retrieveMessagesForTopic(int topicID){
        Query queryRef = getFirebaseRef().child("/topic/" + topicID + "/messages/").orderByChild("time");
        return queryRef;
    }

    public Query retrieveStories(){
        Query queryRef = getFirebaseRef().child("/story/").orderByChild("created");
        return queryRef;
    }

    public Query retrieveTopics(int storyID){
        Query queryRef = getFirebaseRef().child("/topic/").orderByChild("storyID").equalTo(storyID);
        return queryRef;
    }

    public void retrieveTopicFromID(int topicID, ValueEventListener valueEventListener){
        getFirebaseRef().child("/topic/").orderByChild("id").equalTo(topicID).addListenerForSingleValueEvent(valueEventListener);
    }

    public Query retrieveStories(ChildEventListener childEventListener){
        Query queryRef = getFirebaseRef().child("/story/").orderByKey();
        queryRef.addChildEventListener(childEventListener);
        return queryRef;
    }

    public void addListener(ValueEventListener listener){
        getFirebaseRef().addValueEventListener(listener);
    }

    public void addChildListener(ChildEventListener listener){
        getFirebaseRef().addChildEventListener(listener);
    }
}
