package chapter.streams.collects;

import java.util.function.Predicate;

import chapter.streams.utils.Person;

import static java.util.stream.Collectors.*;

public class TestFiltering {
    public static void main(String[] args) {
        Predicate<Person> bigSalaryOnly =
                p -> p.salaries().stream().anyMatch(s -> s>=1500);
        var res = Person.employees.stream()
            .collect(groupingBy(Person::department,
                                filtering(bigSalaryOnly,toList())));
        res.entrySet().forEach(System.out::println);
        System.out.println();
                
        var r = Person.employees.stream()
                .filter(bigSalaryOnly)
                .collect(groupingBy(Person::department));
        r.entrySet().forEach(System.out::println);
        System.out.println();
    }
}
