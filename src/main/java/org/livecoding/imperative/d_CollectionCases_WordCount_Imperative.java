package org.livecoding.imperative;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class d_CollectionCases_WordCount_Imperative {


    public static void main(String[] args) {
        List<String> lines = Arrays.asList("JFall rocks!", "Java8 is almost there");
        int lengthLongestWord = 0;
        for (String line : lines) {
            for (String word : line.split(" ")) {
                if (word.length() > lengthLongestWord) {
                    lengthLongestWord = word.length();
                }
            }
        }
        assertEquals("almost".length(), lengthLongestWord);
    }
}
