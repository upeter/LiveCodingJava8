package org.livecoding.solution;

import static org.livecoding.domain.Person.Role.*;
import org.livecoding.domain.Person;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class e_CollectionCases_Grouping_Final {

    private static final Person PROGRAMMER_Jack_28 = new Person(28, "Jack", PROGRAMMER);
    private static final Person PROGRAMMER_Duke_32 = new Person(32, "Duke", PROGRAMMER);
    private  static final Person TESTER_Jeniffer_32 =new Person(34, "Jeniffer", TESTER);
    private static final Person PROGRAMMER_Erik_24 =new Person(24, "Erik", PROGRAMMER);

    final static List<Person> persons = Arrays.asList(
            PROGRAMMER_Jack_28,
            PROGRAMMER_Duke_32,
            PROGRAMMER_Erik_24,
            TESTER_Jeniffer_32);

    static final Map<Integer, List<Person>> expected = new HashMap(){{
        put(30, Arrays.asList(PROGRAMMER_Duke_32));
        put(20, Arrays.asList(PROGRAMMER_Erik_24, PROGRAMMER_Jack_28));
    }};



    public static void main(String[] args) {
        countLongestWordFunctional();
        computeProgrammersPerAgeGroupFunctional();

    }

    private static void computeProgrammersPerAgeGroupFunctional() {
        Map<Integer, List<Person>> result = persons.stream()
                .filter(p -> p.getRole() == PROGRAMMER)
                .sorted(Comparator.comparing((Person p) -> p.getName()))
                .collect(Collectors.groupingBy(p -> p.getAge() / 10 * 10));
        assertEquals(expected, result);
    }

    private static void countLongestWordFunctional() {
        List<String> lines = Arrays.asList("JFall rocks!", "Java8 is almost there");

        int lengthLongestWord = lines.stream()
                .map(line -> Arrays.asList(line.split(" ")).stream()
                        .map(String::length)
                        .reduce(Math::max).orElse(0))
                .reduce(Math::max).orElse(0);

        assertEquals("almost".length(), lengthLongestWord);
    }
}
