package org.livecoding.solution;

import java.util.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/Collection.html
 * file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/stream/Stream.html
 file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/stream/Collectors.html
 */
public class c_Collections_Final {

    public static void main(String[] args) {

        //show API
        //bulk operations: (mutate collections with functions)
        //-> removeIf
        //streams: collection transformations

        //Collections sample
        //foreach
        Arrays.asList(1, 2, 3, 4).stream().forEach(System.out::println);

        //filter, collect
        //terminal operation versus non terminal operations
        List<Integer> res0 = Arrays.asList(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList());

        assertEquals(Arrays.asList(2, 4), res0);

        //filter, map
        //show error messages: Integer::toString -> is ambigous
        List<String> res1 = Arrays.asList(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i.toString())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("2", "4"), res1);

        //max, Optional
        Optional<Integer> res2 = Arrays.asList(1, 2, 3, 4).stream()
                .max(Comparator.comparing((Integer i) -> i));
        //.max(Integer::compare);
        //.max(Math::max);
        //.collect(Collectors.maxBy(Math::max));
        assertEquals(4, (int) res2.get());

        //orElse
        int res3 = new ArrayList<Integer>().stream()
                .max(Comparator.comparing((Integer i) -> i))
                .orElse(0);
        assertEquals(0, res3);

        //reduce, peek
        Optional<Integer> r = Arrays.asList(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .peek(System.out::println)
                .reduce(Math::addExact);
        System.out.println("res is " + r.get());
        //.reduce((i, j) -> i + j);
    }
}
