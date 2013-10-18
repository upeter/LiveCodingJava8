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

    public static void main(String[] args) {
        String url = "http://twitter/search/tweets...";
        //
        StreamingTweetFilter.processTweets(url, (Tweet tweet) ->
                tweet.getMessage().matches("(Java8|Java 8|java 8|java8)")
        );
        StreamingTweetFilter.processTweets(url, Java8TweetCollector::doProcessTweet);

        Predicate<Tweet> java8Tweets = tweet -> tweet.getMessage().matches("(Java8|Java 8|java 8|java8)");
        Predicate<Tweet> originalTweets =  tweet -> tweet.isRetweet();

        Predicate< Tweet > originalJava8Tweets =  java8Tweets.and(originalTweets);

        StreamingTweetFilter.processTweets(url, originalJava8Tweets);

        //Benefits:
        //Decoupling processing logic from processor -> more reuse, better testable
        //no abuse of inheritance
        //Functions allow for composability

    }

    static class StreamingTweetFilter {

        public static List<Tweet> processTweets(String searchQuery, Predicate<Tweet> doWithTweet) {
            BufferedReader br = null;
            try {
                //do some OAuth...
                //read from stream
                br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()));
                return br.lines()
                        .map(StreamingTweetFilter::parseTweet)
                        .filter(doWithTweet)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(br);
            }
        }

        protected static Tweet parseTweet(String line) {
            return new Tweet(line);
        }
    }


    static class Java8TweetCollector {

        protected static boolean doProcessTweet(Tweet tweet) {
            return tweet.getMessage().matches("(Java8|Java 8|java 8|java8)");

        }
    }




}
