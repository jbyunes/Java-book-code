package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.function.BiConsumer;

import chapter.streams.utils.Person;

public class TestFlatMapping {
    public static void main(String[] args) {
        BiConsumer<String, Integer> printer = (k, v) -> System.out.println(k + " -> " + v);
        var res = Person.employees.stream()
            .collect(groupingBy(Person::name,
                                flatMapping(p -> p.salaries().stream(),
                                            summingInt(x -> x))));
        res.forEach(printer);
        
        var r = Person.employees.stream()
                .collect(groupingBy(
                            Person::name,
                            mapping(Person::salaries,
                                    flatMapping(
                                        List::stream,
                                        summingInt(Integer::intValue)))));
        r.forEach(printer);
    }
}
