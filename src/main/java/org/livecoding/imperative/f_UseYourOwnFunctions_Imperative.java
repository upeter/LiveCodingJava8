package org.livecoding.imperative;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.livecoding.domain.Tweet;

public class f_UseYourOwnFunctions_Imperative {

    public static void main(String[] args) {
        String url = "http://twitter/search/tweets...";

       List<Tweet> collected = new Java8TweetFilter().filterTweets(url);
        System.out.println(collected);
    }

    abstract static class StreamingTweetFilterTemplate {

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


    static class Java8TweetFilter extends StreamingTweetFilterTemplate {
        @Override
        protected boolean doFilterTweet(Tweet tweet) {
            return tweet.getMessage().matches("(Java8|Java 8|java 8|java8)");
        }
    }

    static class OriginalTweetFilter extends StreamingTweetFilterTemplate {
        @Override
        protected boolean doFilterTweet(Tweet tweet) {
            return !tweet.isRetweet();
        }
    }

}
