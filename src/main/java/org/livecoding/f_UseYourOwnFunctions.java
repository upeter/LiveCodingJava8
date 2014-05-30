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
        String url = StreamingTweetFilter.class.getResource("/many_tweets.json").toExternalForm();

    }

    abstract static class StreamingTweetFilter {

        public List<Tweet> filterTweets(String url, Predicate<Tweet> tweetFilter) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
                return br.lines()
                        .parallel()
                        .map(Tweet::new)
                        .filter(tweetFilter)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static class Java8TweetFilter extends StreamingTweetFilter{

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
