package org.livecoding.domain;

public class Tweet {
    private final String message;
    private boolean retweet;
    public Tweet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRetweet() {
        return true;
    }
}
