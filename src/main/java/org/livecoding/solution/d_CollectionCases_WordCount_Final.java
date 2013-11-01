package org.livecoding.solution;

import static org.livecoding.domain.Person.Role.*;

import org.livecoding.domain.Person;

import java.util.*;
import java.util.stream.Collectors;

import org.livecoding.domain.Person.*;

import static org.junit.Assert.assertEquals;

public class d_CollectionCases_WordCount_Final {


    public static void main(String[] args) {
        countLongestWordFunctional();
    }

    private static void countLongestWordFunctional() {
        List<String> lines = Arrays.asList("JFall rocks!", "Java8 is almost there");

        int lengthLongestWord = lines.parallelStream()
                .map(line -> Arrays.asList(line.split(" ")).stream()
                        .map(String::length)
                        .reduce(Math::max).orElse(0))
                .reduce(Math::max).orElse(0);


        lengthLongestWord = lines.stream()
                .mapToInt(line -> Arrays.asList(line.split(" ")).stream()
                        .mapToInt(String::length)
                        .max().orElse(0))
                .max().orElse(0);

        assertEquals("almost".length(), lengthLongestWord);
    }

}
