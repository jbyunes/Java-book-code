package chapter.streams.collects;

import static java.util.stream.Collectors.*;

import java.util.TreeMap;
import java.util.function.BinaryOperator;

import chapter.streams.utils.Person;

public class TestToMap {
    public static void main(String[] args) {
        var res = Person.employees.stream()
            .collect(toMap(Person::name,Person::salary));
        System.out.println(res);
        
        BinaryOperator<String> concat = (o,n) -> o+":"+n;

        var r = Person.employees.stream()
            .collect(toMap(Person::salary, Person::name, concat));
        System.out.println(r);

        var result = Person.employees.stream()
            .collect(toMap(Person::salary,
                           Person::name,
                           concat,
                           TreeMap::new));
        System.out.println(result);
    }
}
