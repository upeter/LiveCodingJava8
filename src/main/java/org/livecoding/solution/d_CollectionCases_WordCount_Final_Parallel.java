package org.livecoding.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class d_CollectionCases_WordCount_Final_Parallel {


    public static void main(String[] args) {
        countLongestWordParallelFunctional();
    }

    private static void countLongestWordParallelFunctional() {
        List<String> lines = getManyLines();
        long current = System.currentTimeMillis();

        int lengthLongestWord = lines.parallelStream()
                .map(line -> Arrays.asList(line.split(" ")).stream()
                        .map(String::length)
                        .reduce(Math::max).orElse(0))
                .reduce(Math::max).orElse(0);

        long elapsed = System.currentTimeMillis() - current;
        System.out.println("Elapsed " + elapsed);
    }

    public static List<String> getManyLines() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < 20000000; i++) {
            lines.add("JFall rocks " + new Integer(i).toString() + " times!");
        }
        return lines;
    }
}
