package org.livecoding;


import java.util.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 *
 */
public class c_Collections {

    public static void main(String[] args) {
        //Collections samples:
        //foreach

        //filter, groupBy

        //max
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



