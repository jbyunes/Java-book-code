package chapter.streams.collects;

import static java.util.stream.Collectors.counting;

import chapter.streams.utils.Person;

public class TestCounting {
    public static void main(String[] args) {
        var c = Person.employees.stream()
            .peek(System.out::println)
            .count();
        System.out.println(c);
            
        var count = Person.employees.stream()
            .peek(System.out::println)
            .collect(counting());
        System.out.println(count);
    }
}
