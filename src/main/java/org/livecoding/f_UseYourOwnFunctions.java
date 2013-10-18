package org.livecoding;

import org.apache.commons.io.IOUtils;
import org.livecoding.domain.Tweet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class f_UseYourOwnFunctions {

    public static void main(String[] args) {
        String url = "http://twitter/search/tweets...";
        //replace with predicate lambda

        //replace with predicate method reference

        //combine predicates

    }

    abstract static class StreamingTweetFilter {

        public List<Tweet> filterTweets(String searchQuery) {
            List<Tweet> collected = new ArrayList<>();
            BufferedReader br = null;
            try {
                //do some OAuth...
                //read from stream
                br = new BufferedReader(new InputStreamReader(new URL(searchQuery).openStream()));
                String line = "";
                while (line != null) {
                    line = br.readLine();
                    Tweet tweet = parseTweet(line);
                    //do something specific with the tweet in doProcessTweet(...)
                    if(doFilterTweet(tweet)) {
                        collected.add(tweet);
                    }
                }
            } catch (IOException e) {
                IOUtils.closeQuietly(br);
                throw new RuntimeException(e);
            } finally {
                IOUtils.closeQuietly(br);
            }
            return collected;
        }

        abstract boolean doFilterTweet(Tweet tweet);

        protected static Tweet parseTweet(String line) {
            return new Tweet(line);
        }
    }


    static class Java8TweetFilter extends StreamingTweetFilter {
        @Override
        protected boolean doFilterTweet(Tweet tweet) {
            return tweet.getMessage().matches("(Java8|Java 8|java 8|java8)");
        }
    }




}