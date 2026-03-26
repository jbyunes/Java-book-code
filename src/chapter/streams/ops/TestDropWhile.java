package chapter.streams.ops;

import java.util.stream.IntStream;

import chapter.streams.utils.Person;

import static java.util.Comparator.*;

import java.util.Random;

public class TestDropWhile {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i<200, i -> i + 3)
            .dropWhile(i -> i / 3 < 50)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
        
        System.out.println();
        Person.employees.stream()
            .sorted(comparing(Person::salary))
            .dropWhile(p -> p.salary()<1500)
            .forEach(System.out::println);
        
        System.out.println();
        Person.employees.stream()
            .filter(p -> p.salary()<1500)
            .sorted(comparing(Person::salary))
            .forEach(System.out::println);

        System.out.println();
        var r = new Random(666);
        r.ints()
            .dropWhile(i -> i<0)
            .limit(10)
            .forEach(System.out::println);

    }
}
