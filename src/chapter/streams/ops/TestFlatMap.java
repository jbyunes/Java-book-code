package chapter.streams.ops;

import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import chapter.streams.utils.Person;

public class TestFlatMap {
    public static void main(String[] args) {
        Person.employees.stream() // Stream<Person>
            .map(Person::salaries) // Stream<List<Integer>>
            .flatMap(Collection::stream) // Stream<Integer>
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
        
        Person.employees.stream()
            .map(Person::salaries) // Stream<List<Integer>>
            .flatMap(Collection::stream) // Stream<Integer>
            .distinct()
            .sorted()
            .forEach(i -> System.out.print(i+" "));
        System.out.println();

        
        IntStream.of(0, 1, 2, 3)
            .mapToObj(i -> new Random().ints(i*10,(i+1)*10))
            .flatMapToInt(s -> s.limit(5))
            .forEach(i -> System.out.print(i+" "));
        System.out.println();

        final Random rnd = new Random();
        IntStream.of(0, 1, 2, 3)
            .mapToObj( i -> rnd.ints(i*10,(i+1)*10))
            .flatMapToInt(s -> s.limit(5))
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
    }
}
