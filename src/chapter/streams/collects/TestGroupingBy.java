package chapter.streams.collects;

import static java.util.stream.Collectors.*;
import java.util.HashSet;
import java.util.TreeMap;

import chapter.streams.utils.Person;

public class TestGroupingBy {
    public static void main(String[] args) {
        var res = Person.employees.stream()
            .collect(groupingBy(Person::department, counting()));
        res.entrySet().forEach(System.out::println);
        System.out.println();
        
        var r = Person.employees.stream()
            .collect(groupingBy(Person::salary));
        r.forEach((k, v) -> System.out.println(k + " -> " + v));
        System.out.println();

        var result = Person.employees.stream()
            .collect(groupingBy(Person::department, toSet()));
        result.entrySet().forEach(System.out::println);
        System.out.println();

        var s = Person.employees.stream()
            .collect(groupingBy(Person::department,toCollection(HashSet::new)));
        s.forEach((k, v) -> System.out.println(k + " -> " + v));
        System.out.println();

        var sorted = Person.employees.stream()
            .collect(groupingBy(Person::department, TreeMap::new, toSet()));
        sorted.entrySet().forEach(System.out::println);
        System.out.println();

        var byServBySal = Person.employees.stream()
            .collect(groupingBy(Person::department,
                                groupingBy(Person::salary)));
        byServBySal.forEach((srv, bySal) -> {
            System.out.println(srv);
            bySal.forEach((sal, pers) -> {
                System.out.println("\t"+sal);
                System.out.println("\t\t"+pers);
            });
        });
    }
}
