package chapter.streams.collects;

import static java.util.stream.Collectors.joining;

import chapter.streams.utils.Person;

public class TestJoining {
    public static void main(String[] args) {
        String result = Person.employees.stream()
            .map(Person::name)
            .collect(joining());
        System.out.println(result);

        String r = Person.employees.stream()
            .map(Person::name)
            .collect(joining(", "));
        System.out.println(r);

        String res = Person.employees.stream()
            .map(Person::name)
            .collect(joining(", ", "La liste des employés: ", "."));
        System.out.println(res);
    }
}
