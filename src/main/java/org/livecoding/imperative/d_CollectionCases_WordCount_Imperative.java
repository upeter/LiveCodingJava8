package org.livecoding.imperative;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class d_CollectionCases_WordCount_Imperative {

    static final  List<String> lines = Arrays.asList("JFall rocks!", "Java8 is almost there");

    public static void main(String[] args) {

        int lengthLongestWord = 0;

        for (String tweet : lines) {
            for (String word : tweet.split(" ")) {
                if (word.length() > lengthLongestWord) {
                    lengthLongestWord = word.length();
                }
            }
        }
        assertEquals("almost".length(), lengthLongestWord);
    }
}
