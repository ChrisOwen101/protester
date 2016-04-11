package com.protester.pojo;

import com.newschat.newschat.helper.PreferenceHelper;

public class ChatMessage {

    private String message;
    private String author;
    private long time;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private ChatMessage() {
    }

    public ChatMessage(String message) {
        this.message = message;
        this.author = PreferenceHelper.get().getUserName();
        this.time = System.currentTimeMillis()/1000;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public long getTime() {
        return time;
    }
}
