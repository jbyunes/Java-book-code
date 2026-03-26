package chapter.streams.collects;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class TestSimpleCollect {
    public static void main(String[] args) {
        Object[] wellPaid = Person.employees.stream()
            .filter(p -> p.salary()>=1500)
            .toArray();
        for (var p : wellPaid) {
            System.out.println(p);
        }
        System.out.println();

        var wellPaidTyped = Person.employees.stream()
            .filter(p -> p.salary()>=1500)
            .toArray(Person[]::new);
        for (var p : wellPaidTyped) {
            System.out.println(p);
        }
        System.out.println();

        List<String> l = Person.employees.stream()
            .map(Person::name)
            .filter(s -> s.startsWith("J"))
            .toList();
        System.out.println(l);
        
        var salaries = Person.employees.stream()
            .map(Person::salary)
            .collect(HashSet<Integer>::new,
                     Collection::add,
                     Collection::addAll);
        System.out.println(salaries);
        
        var result = Stream.iterate(0,i->i+1)
            .limit(20)
            .filter(i -> i%2==0)
            .collect(HashSet<Integer>::new,
                    Collection::add,
                    Collection::addAll);
        System.out.println(result);
        
        var s = Person.employees.stream()
            .map(Person::name)
            .filter(n -> n.charAt(0) == 'J')
            .collect(StringBuilder::new,
                     (b,st) -> { if (b.length()!=0) b.append(", "); b.append(st); },
                     StringBuilder::append)
            .toString();
        System.out.println(s);
    }
}
