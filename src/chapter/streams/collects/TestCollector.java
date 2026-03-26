package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import java.util.IntSummaryStatistics;
import java.util.LinkedList;

import chapter.streams.utils.Person;

public class TestCollector {
    public static void main(String[] args) {
        var meanSalary = Person.employees.stream()
            .collect(averagingInt(Person::salary));
        System.out.println(meanSalary);
        
        var wellPaid = Person.employees.stream()
            .filter(p -> p.salary()>=1500)
            .collect(toCollection(LinkedList::new));
        wellPaid.forEach(System.out::println);
        System.out.println();

        
        var mean = Person.employees.stream()
            .mapToDouble(Person::salary)
            .average();
        System.out.println(mean.orElse(Double.NaN));

        var res = Person.employees.stream()
            .collect(summarizingInt(Person::salary));
        System.out.println(res);
        
        final var s = new IntSummaryStatistics();
        Person.employees.stream()
                .mapToInt(Person::salary)
                .forEach(s);
        System.out.println(s);

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
