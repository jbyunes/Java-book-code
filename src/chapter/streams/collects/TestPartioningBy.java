package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import chapter.streams.utils.Person;

public class TestPartioningBy {
    public static void main(String[] args) {
        var res = Person.employees.stream()
            .collect(partitioningBy(p -> p.salary()>1200));
        res.entrySet().forEach(System.out::println);
    }
}
