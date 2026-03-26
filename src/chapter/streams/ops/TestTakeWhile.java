package chapter.streams.ops;

import static java.util.Comparator.*;

import java.util.stream.IntStream;

import chapter.streams.utils.Person;

public class TestTakeWhile {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 3)
        .takeWhile(i -> i / 3 < 50)
        .limit(5)
        .forEach(i -> System.out.print(i+" "));
        System.out.println();

        Person.employees.stream()
            .sorted(comparing(Person::salary))
            .takeWhile(p -> p.salary()<1500)
            .forEach(System.out::println);
    }

}
