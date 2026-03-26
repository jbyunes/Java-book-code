package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import java.util.Collections;

import chapter.streams.utils.Person;

public class TestAndThen {
    public static void main(String[] args) {
        var list = Person.employees.stream()
            .collect(collectingAndThen(toList(),
                                       Collections::unmodifiableList));
        list.forEach(System.out::println);
    }
}
