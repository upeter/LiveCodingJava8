package org.livecoding.imperative;

import org.livecoding.domain.Person;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.livecoding.domain.Person.Role.PROGRAMMER;
import static org.livecoding.domain.Person.Role.TESTER;

public class e_CollectionCases_Grouping_Imperative {


    private static final Person PROGRAMMER_Jack_28 = new Person(28, "Jack", PROGRAMMER);
    private static final Person PROGRAMMER_Duke_32 = new Person(32, "Duke", PROGRAMMER);
    private static final Person TESTER_Jeniffer_32 = new Person(34, "Jeniffer", TESTER);
    private static final Person PROGRAMMER_Erik_24 = new Person(24, "Erik", PROGRAMMER);

    public final static List<Person> persons = Arrays.asList(
            PROGRAMMER_Jack_28,
            PROGRAMMER_Duke_32,
            PROGRAMMER_Erik_24,
            TESTER_Jeniffer_32);

    public static final Map<Integer, List<Person>> expected = new HashMap() {{
        put(30, Arrays.asList(PROGRAMMER_Duke_32));
        put(20, Arrays.asList(PROGRAMMER_Erik_24, PROGRAMMER_Jack_28));
    }};


    public static void main(String[] args) {

        //filter programmers
        List<Person> programmers = new ArrayList<>();
        for (Person p : persons) {
            if (p.getRole() == PROGRAMMER) {
                programmers.add(p);
            }
        }

        //sort
        Collections.sort(programmers, new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        //group by age group
        Map<Integer, List<Person>> programmersPerAgeGroup = new HashMap<>();
        for (Person programmer : programmers) {
            int ageGroup = programmer.getAge() / 10 * 10;
            List<Person> ageGroupProgrammers = programmersPerAgeGroup.getOrDefault(ageGroup, new ArrayList<Person>());
            ageGroupProgrammers.add(programmer);
            programmersPerAgeGroup.put(ageGroup, ageGroupProgrammers);
        }

        assertEquals(expected, programmersPerAgeGroup);
    }


}
