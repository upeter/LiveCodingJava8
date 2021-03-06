package org.livecoding;

import org.livecoding.domain.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class f_UseYourOwnFunctions {
    public static final String JAVA_8_REGEXP = ".*[Jj][Aa][Vv][Aa][\\s]?8.*";

    public static void main(String[] args) {
        String url = StreamingTweetFilter.class.getResource("/tweets.json").toExternalForm();

    }

    abstract static class StreamingTweetFilter {

        public List<Tweet> filterTweets(String url) {
            List<Tweet> collected = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
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


    static class Java8TweetFilter extends StreamingTweetFilter {

        protected boolean doFilterTweet(Tweet tweet) {
            return tweet.getMessage().matches(JAVA_8_REGEXP);

        }
    }


























    public static <T> T measure(Supplier<T> code) {
        long current = System.currentTimeMillis();
        T result = code.get();
        System.out.printf("Time elapsed: %s ms\n", (System.currentTimeMillis() - current));
        return result;
    }


}
