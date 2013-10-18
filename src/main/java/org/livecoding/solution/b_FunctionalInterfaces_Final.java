package org.livecoding.solution;

import java.io.File;
import java.util.function.*;

public class b_FunctionalInterfaces_Final {

    public static void main(String[] args) {
        functionalInterfacesSamples();
    }

    private static void functionalInterfacesSamples() {
        //Functional interfaces

        //Supplier: no input, only output
        Supplier<Integer> supplier1 = () -> "How Long am I?".length();
        Supplier<Integer> supplier = "How Long am I?"::length;
        //Instance method reference
        Supplier<Object> supplier2 = Object::new;

        //Consumer: input, no output
        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> consumer2 = System.out::println;

        //Function: input and output
        Function<String, Integer> function = String::length;
        Function<Integer, Integer> square = i -> i * i;
        System.out.println(square.compose(function).apply("Testing"));

        BiFunction<Integer, Integer, String> bif = (i, j) -> Integer.toString(i + j);
        bif.apply(1, 2);

        //Predicate: input and boolean output
        Predicate<File> isTxt = f -> f.getName().endsWith(".txt");
        Predicate<File> canWrite = File::canWrite;

        Predicate<File> writeableTxt = isTxt.and(canWrite);
        System.out.println(writeableTxt.test(new File("/tmp/demo/readable.txt")));

    }
}
