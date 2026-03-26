package chapter.streams.ops;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class TestAnyMatchNoneMatch {
    private static boolean test(BiPredicate<Stream<Person>,Predicate<Person>> m, String n) {
        return m.test(Person.employees.stream(), p -> p.name().equals(n));
    }
    public static void main(String[] args) {
        System.out.println(test(Stream::anyMatch, "Joe"));
        System.out.println(test(Stream::anyMatch, "Tom"));
        System.out.println(test(Stream::noneMatch,"Joe"));
    }
}
