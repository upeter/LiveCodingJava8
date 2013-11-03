package org.livecoding;

import org.livecoding.domain.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class f_UseYourOwnFunctions {
    public static final String JAVA_8_REGEXP = ".*[Jj][Aa][Vv][Aa][\\s]?8.*";

    public static void main(String[] args) {
        String url = StreamingTweetFilter.class.getResource("/tweets.csv").toExternalForm();
    }

    abstract static class StreamingTweetFilter {

        public List<Tweet> filterTweets(String searchQuery) {
            List<Tweet> collected = new ArrayList<>();
            //do some OAuth...
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()))) {
                String json = "";
                while ((json = br.readLine())!= null) {
                    Tweet tweet = new Tweet(json);
                    //do something specific with the tweet in doFilterTweet(...)
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


    static class Java8TweetFilter {

        protected static boolean doFilterTweet(Tweet tweet) {
            return tweet.getMessage().matches(JAVA_8_REGEXP);

        }
    }


























    public static <T> T measure(Supplier<T> code) {
        long current = System.currentTimeMillis();
        T result = code.get();
        long elapsed = System.currentTimeMillis() - current;
        System.out.println(elapsed);
        return result;
    }


}
