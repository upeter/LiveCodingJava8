package org.livecoding.solution;

import org.livecoding.domain.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/Collection.html
 * file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/stream/Stream.html
 * file:///Users/urs/Documents/jdk-8-ea-docs-b99-all-17_jul_2013/docs/api/index.html?java/util/stream/Collectors.html
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

        //filter, collect
        List<Integer> res0b = IntStream.of(4, 3, 2, 1).filter(i -> i % 2 == 0)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        IntStream.rangeClosed(1, 5).forEach(o -> System.out.println(o));

        //filter, map
        List<String> filter = Arrays.asList(4, 3, 2, 1).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i.toString())
                .collect(Collectors.toList());


        List<String> collect = Arrays.asList(new Person("Jack", 20), new Person("Benjamin", 5), new Person("Bill", 35)).stream()
                .filter(p -> p.getAge() >= 18)
                .map(p -> p.getName())
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);

        //filter, map
        //show error messages: Integer::toString -> is ambigous
        List<String> res1 = Arrays.asList(1, 2, 3, 4).stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i.toString())
                .collect(Collectors.toList());

        assertEquals(Arrays.asList("2", "4"), res1);

        //max, Optional
        Optional<Integer> res2 = Arrays.asList(1, 2, 3, 4).stream()
                // .max(Comparator.comparing((Integer i) -> i));
                //.max(Integer::compare);
                .max(Comparator.<Integer>naturalOrder());
        //.collect(Collectors.maxBy(Math::max));

        assertSame(4, res2.get());
        assertTrue(res2.isPresent());
        assertSame(4, res2.orElse(0));

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


        //groupBy
        Map<Integer, List<Integer>> mapRes = Arrays.asList(1, 2, 3, 4).stream()
                .collect(Collectors.groupingBy(i -> i % 2));

        Map<Integer, List<Integer>> gb1 = Arrays.asList(1, 2, 3, 4, 7, 9, 11).stream()
                .collect(Collectors.groupingBy((Integer i) -> i % 2));

        Map<Integer, Integer> gb2 = gb1.entrySet().stream()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().size()));
    }

    static class Person {

        String name;
        int age;
        public Person(String name, int age) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        public String toString() {
            return String.format("Person[name=%s, age=%s]", name, age);
        }
    }
}
