package org.livecoding.solution;

import org.apache.commons.io.IOUtils;
import org.livecoding.domain.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class f_UseYourOwnFunctions_Final {
    public static final String JAVA_8_REGEXP = ".*[Jj][Aa][Vv][Aa][\\s]?8.*";

    static class StreamingTweetFilter {

        public static List<Tweet> processTweets(String searchQuery, Predicate<Tweet> doFilterTweet) {
            //do some OAuth...
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()))) {
                return br.lines()
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

        String url = StreamingTweetFilter.class.getResource("/tweets.csv").toExternalForm();
        //
        List<Tweet> collected = StreamingTweetFilter.processTweets(url, (Tweet tweet) ->
                tweet.getMessage().matches(JAVA_8_REGEXP)
        );

        System.out.println(collected);
        StreamingTweetFilter.processTweets(url, Java8TweetCollector::doProcessTweet);

        Predicate<Tweet> java8Tweets = tweet -> tweet.getMessage().matches(JAVA_8_REGEXP);
        Predicate<Tweet> originalTweets =  tweet -> tweet.isRetweet();

        Predicate< Tweet > originalJava8Tweets =  java8Tweets.and(originalTweets);

        collected = StreamingTweetFilter.processTweets(url, originalJava8Tweets);
        System.out.println(collected);
        //Benefits:
        //Decoupling processing logic from processor -> more reuse, better testable
        //no abuse of inheritance
        //Functions allow for composability

    }


}
