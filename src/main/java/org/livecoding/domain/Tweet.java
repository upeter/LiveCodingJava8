package org.livecoding.domain;

public class Tweet {
    private final String message;
    public Tweet(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRetweet() {
        return message.length() % 2 == 0;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "message='" + message + '\'' +
                ", retweet=" + isRetweet() +
                '}';
    }


}
