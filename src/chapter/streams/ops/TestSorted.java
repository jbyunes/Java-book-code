package chapter.streams.ops;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class TestSorted {
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        Random r = new Random(666);
        IntStream.generate(r::nextInt)
            .limit(10)
            .sorted()
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
        
        Stream.of("Joe","Jack","William","Averell")
            .sorted()
            .forEach(System.out::println);
        System.out.println();

        Stream.of("double", "Hello", "elephant", "Agent", "concrete")
            .sorted()
            .forEach(System.out::println);
        System.out.println();

        Stream.of("double", "Hello", "elephant", "Agent", "concrete")
            .sorted(String::compareToIgnoreCase)
            .forEach(System.out::println);
        System.out.println();
        
        var byAscName       = Comparator.comparing(Person::name);
        var byDesName       = byAscName.reversed();
        var byAscDepartment = Comparator.comparing(Person::department);
        var byDesDepartment = byAscDepartment.reversed();
        var byAscSalary     = Comparator.comparing(Person::salary);
        var byDesSalary     = byAscSalary.reversed();
        
        Person.employees.stream()
            .sorted(byDesName)
            .forEach(System.out::println);
        System.out.println();
        
        Person.employees.forEach(System.out::println);
        System.out.println();
        
        Person.employees.stream()
            .sorted(byAscDepartment.thenComparing(byDesName))
            .forEach(System.out::println);

        var byName       = Comparator.comparing(Person::name);
        var byDepartment = Comparator.comparing(Person::department);
        var bySalary     = Comparator.comparing(Person::salary);
        Person.employees.stream()
            .sorted(byDepartment.thenComparing(byName.reversed()))
            .forEach(System.out::println);

    }

}
