package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import chapter.streams.utils.Person;

public class TestMapping {
    public static void main(String[] args) {
        var res = Person.employees.stream()
            .collect(groupingBy(Person::salary,
                                mapping(Person::name,joining(", "))));
        res.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}
