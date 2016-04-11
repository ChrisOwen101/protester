package com.protester.pojo;

public class UserAccount {

    private String username;

    @SuppressWarnings("unused")
    private UserAccount() {
    }

    public UserAccount(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
