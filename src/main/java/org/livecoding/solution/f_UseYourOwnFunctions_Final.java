package org.livecoding.solution;

import org.livecoding.domain.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class f_UseYourOwnFunctions_Final {
    public static final String JAVA_8_REGEXP = ".*[Jj][Aa][Vv][Aa][\\s]?8.*";

    static class StreamingTweetFilter {

        public static List<Tweet> processTweets(String searchQuery, Predicate<Tweet> doFilterTweet) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()))) {
                return br.lines()
                        .parallel()
                        .map(Tweet::new)
                        .filter(doFilterTweet)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static class Java8TweetCollector {

        protected static boolean doProcessTweet(Tweet tweet) {
            return tweet.getMessage().matches(JAVA_8_REGEXP);

        }
    }


    public static void main(String[] args) {

        String url = StreamingTweetFilter.class.getResource("/many_tweets.csv").toExternalForm();
        measure(() -> StreamingTweetFilter.processTweets(url, Java8TweetCollector::doProcessTweet));
        //
        List<Tweet> collected = StreamingTweetFilter.processTweets(url, (Tweet tweet) ->
                tweet.getMessage().matches(JAVA_8_REGEXP)
        );

        System.out.println(collected);
        StreamingTweetFilter.processTweets(url, Java8TweetCollector::doProcessTweet);

        Predicate<Tweet> java8Tweets = Java8TweetCollector::doProcessTweet;
        Predicate<Tweet> retweets = Tweet::isRetweet;

        Predicate<Tweet> originalJava8Tweets = java8Tweets.and(retweets.negate());

        collected = StreamingTweetFilter.processTweets(url, originalJava8Tweets);
        System.out.println(collected);

        //Benefits:
        //Decoupling processing logic from processor -> more reuse, better testable
        //no abuse of inheritance
        //Functions allow for composability

    }

    public static <T> T measure(Supplier<T> code) {
        long current = System.currentTimeMillis();
        T result = code.get();
        long elapsed = System.currentTimeMillis() - current;
        System.out.println(elapsed);
        return result;
    }

}
