package org.livecoding.domain;

public class Tweet {
    private final String message;
    public Tweet(String message) {
        this.message = message;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            //do nothing
        }
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
