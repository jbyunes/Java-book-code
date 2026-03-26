package chapter.streams.intro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.*;
import java.util.List;

import chapter.streams.utils.Person;

public class StreamExample {
    public static void main(String[] args) {
        // extract employees from mail department
        List<Person> employeesMail = new ArrayList<>();
        for (Person p : Person.employees) {
            if (p.department().equals("mail")) {
                employeesMail.add(p);
            }
        }
        // sort by "descending" salaries
        Collections.sort(employeesMail, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o2.salary(), o1.salary());
            }
        });
        // collect names
        List<String> names = new ArrayList<>();
        for (Person p : employeesMail) {
            names.add(p.name());
        }
        // print results
        System.out.println("---------");
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println();

        List<String> mailNames = Person.employees.stream()
            .filter(p -> p.department().equals("mail"))
            .sorted(comparing(Person::salary).reversed())
            .map(Person::name)
            .toList();
        System.out.println("---------");
        mailNames.forEach(System.out::println);
        System.out.println();        
    }
}