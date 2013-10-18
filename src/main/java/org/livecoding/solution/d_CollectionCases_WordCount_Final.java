package org.livecoding.solution;

import static org.livecoding.domain.Person.Role.*;

import org.livecoding.domain.Person;

import java.util.*;
import java.util.stream.Collectors;

import org.livecoding.domain.Person.*;

import static org.junit.Assert.assertEquals;

public class d_CollectionCases_WordCount_Final {


    public static void main(String[] args) {
        //countLongestWordFunctional();
        countLongestWordParallelFunctional();

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


    private static void countLongestWordParallelFunctional() {
        List<String> lines = getManyLines();
        long current = System.currentTimeMillis();

        int lengthLongestWord = lines.parallelStream()
                .map(line -> Arrays.asList(line.split(" ")).stream()
                        .map(String::length)
                        .reduce(Math::max).orElse(0))
                .reduce(Math::max).orElse(0);
        assertEquals("99999999".length(), lengthLongestWord);
        long elapsed = System.currentTimeMillis() - current;
        System.out.println("Elapsed " + elapsed);
    }

    public static List<String> getManyLines() {
        List<String> lines  = new ArrayList<>();
        for (int i = 0; i < 20000000; i++) {
            lines.add("JFall rocks " + new Integer(i).toString() + " times!");
        }
        return lines;
    }
}
