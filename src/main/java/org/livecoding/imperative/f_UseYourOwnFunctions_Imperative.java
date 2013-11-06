package org.livecoding.imperative;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.livecoding.domain.Tweet;

public class f_UseYourOwnFunctions_Imperative {

    abstract static class StreamingTweetFilterTemplate {

        public List<Tweet> filterTweets(String searchQuery) {
            List<Tweet> collected = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()))) {
                String json = "";
                while ((json = br.readLine()) != null) {
                    Tweet tweet = new Tweet(json);
                    //filter tweets
                    if (doFilterTweet(tweet)) {
                        collected.add(tweet);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return collected;
        }

        abstract boolean doFilterTweet(Tweet tweet);

    }


    static class Java8TweetFilter extends StreamingTweetFilterTemplate {
        public static final String JAVA_8_REGEXP = ".*[Jj][Aa][Vv][Aa][\\s]?8.*";

        @Override
        protected boolean doFilterTweet(Tweet tweet) {
            return tweet.getMessage().matches(JAVA_8_REGEXP);
        }
    }

    static class OriginalTweetFilter extends StreamingTweetFilterTemplate {
        @Override
        protected boolean doFilterTweet(Tweet tweet) {
            return !tweet.isRetweet();
        }
    }


    public static void main(String[] args) {
        String url = StreamingTweetFilterTemplate.class.getResource("/tweets.json").toExternalForm();
        long current = System.currentTimeMillis();
        List<Tweet> collected = new Java8TweetFilter().filterTweets(url);
        System.out.printf("Time elapsed: %s ms\n", (System.currentTimeMillis() - current));
    }

















    public static <T> T measure(Supplier<T> code) {
        long current = System.currentTimeMillis();
        T result = code.get();
        System.out.printf("Time elapsed: %s ms\n", (System.currentTimeMillis() - current));
        return result;
    }


}
