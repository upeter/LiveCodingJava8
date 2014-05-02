package org.livecoding.solution;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static void moreSamples() {


    System.out.println(IntStream.builder().add(1).add(3).build().sum());

    System.out.println(IntStream.rangeClosed(1, 3).sum());
    System.out.println(Arrays.stream(new int [] {1, 2, 3}).sum());
    System.out.println(Arrays.asList(1, 2, 3).stream().sorted().collect(Collectors.toList()));
    System.out.println(Arrays.asList(1, 2, 3).stream().sorted().collect(Collectors.toList()));
    //System.out.println(Arrays.asList(new Person(), new Person(), new Person()).stream().sorted().collect(Collectors.toList()));


    //Javadoc: @FunctionalInterface
    Map<String, Integer> map = new HashMap<>();
    map.put("java", 7);
    map.merge("j", 9, (o, n) -> o);
    System.out.println(map);
    BiFunction<Integer, Integer, Integer> bi = Math::max;
    map.put("java", 7);
    map.merge("java", 8, Math::max);
    System.out.println(map);


    //Supplier no input, only output
    Supplier<Integer> s1 = () -> "How long am I?".length();
    Supplier<Integer> s2 = "How long am I?"::length;
    Supplier<Object> s3 = Object::new;
    System.out.println(s1.get());

    //Consumer: input, no output
    Consumer<Object> c1 = o -> System.out.println(o);
    c1.accept("Hallo");
    c1.andThen(System.out::println).accept("Hi");

    //Function: input and output
    int multiplier = 3;
    Function<Integer, Integer> square = i -> i * multiplier;
    Function<Integer, String> toString = e -> e.toString();

    Function<Integer, String> square2 = in -> String.format("square is: %s", (in * in)) ;
    System.out.println(square.apply(2));
    Function<Integer, String> res = square.compose(square).andThen(toString);
    System.out.println(res.apply(2));

    //Predicate: input and boolean output
    Predicate<File> p1 = f -> f.isDirectory();
    FileFilter ff = f -> f.isDirectory();

    Map<Integer, List<Integer>> intermediate = Arrays.asList(23,23,34,45,26).stream().collect(Collectors.groupingBy(i -> i % 2)) ;
    Map<Integer, Integer> result = intermediate.entrySet().stream().collect(Collectors.toMap((Map.Entry<Integer, List<Integer>> k) -> k.getKey(), v -> v.getValue().size()));
    Arrays.asList(23,24,34,45,26).stream().collect(Collectors.toMap(e -> e, e -> e));
    Arrays.asList(23,24,34,45,26).stream().collect(Collectors.toSet());


    Map<Integer, Integer> r1 = Arrays.asList(23,23,34,45,26).stream().collect(Collectors.groupingBy(i -> i % 2)).entrySet().stream().collect(Collectors.toMap((Map.Entry<Integer, List<Integer>> k) -> k.getKey(), v -> v.getValue().size()));

    }
}
