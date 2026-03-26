package chapter.streams.comparator;

import static java.util.Comparator.*;

import chapter.streams.utils.Person;

public class ComparatorExample {
    public static void main(String[] args) {
//        Comparator<Person> byAscName       =
//            (p1, p2) -> p1.name().compareTo(p2.name());
//        Comparator<Person> byDecName       =
//            (p1, p2) -> p2.name().compareTo(p1.name());
//        Comparator<Person> byAscDepartment =
//            (p1, p2) -> p1.department().compareTo(p2.department());
//        Comparator<Person> byDecDepartment =
//            (p1, p2) -> p2.department().compareTo(p1.department());
//        Comparator<Person> byAscSalary     =
//            (p1, p2) -> Integer.compare(p1.salary(), p2.salary());
//        Comparator<Person> byDecSalary     =
//            (p1, p2) -> Integer.compare(p2.salary(), p1.salary());
            
        var byName       = comparing(Person::name);
        var byDepartment = comparing(Person::department);
        var bySalary     = comparing(Person::salary);
                
        Person.employees.stream()
            .sorted(byName)
            .forEach(System.out::println);
        System.out.println();
        Person.employees.stream()
            .sorted(byDepartment.reversed())
            .forEach(System.out::println);
    }
}
