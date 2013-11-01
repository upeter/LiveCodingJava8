package org.livecoding;

import org.livecoding.domain.Person;

import static org.livecoding.domain.Person.Role.*;

import java.util.*;

import static org.junit.Assert.*;

public class d_CollectionCases_WordCount {

    static final List<String> lines = Arrays.asList("JFall rocks!", "Java 8 is almost there");

    public static void main(String[] args) {
        int lengthLongestWord = 0;

        assertEquals("almost".length(), lengthLongestWord);
    }






































    public static List<String> getManyLines() {
        List<String> lines  = new ArrayList<>();
        for (int i = 0; i < 20000000; i++) {
            lines.add("JFall rocks " + new Integer(i).toString() + " times!");
        }
        return lines;
    }



}
