package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;

import chapter.streams.utils.Person;

public class TestMinByMaxBy {
    private static Map<String,Integer>
        extractSalary(Collector<Integer,?,Optional<Integer>> c) {
        return Person.employees.stream()
            .collect(groupingBy(Person::name,
                         collectingAndThen(
                             flatMapping(p -> p.salaries().stream(),c),
                             o -> o.orElse(0))));
    }
    public static void main(String[] args) {
        System.out.println(extractSalary(maxBy(Integer::compareTo)));
        System.out.println(extractSalary(minBy(Integer::compareTo)));
    }
}
