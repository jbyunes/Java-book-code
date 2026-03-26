package chapter.streams.ops;

import static java.util.Comparator.*;

import chapter.streams.utils.Person;

public class TestMap {
    public static void main(String[] args) {
        System.out.println("--------------map--------------");
        Person.employees.stream()
            .sorted(comparing(Person::salary))
            .map(Person::name)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();

        Person.employees.stream()
            .sorted(comparing(Person::salary))
            .map(Person::name)
            .map(String::toUpperCase)
            .map(s -> s.concat("_DALTON"))
            .forEach(i -> System.out.print(i+" "));
        System.out.println();        
    }
}
