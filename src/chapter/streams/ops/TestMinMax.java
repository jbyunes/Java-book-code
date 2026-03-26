package chapter.streams.ops;

import static java.util.Comparator.*;

import chapter.streams.utils.Person;

public class TestMinMax {
    public static void main(String[] args) {
        var op = Person.employees.stream()
            .min(comparing(Person::salary));
        var s = op.map(p -> p.name()+" has the least salary").orElse("no one");
        System.out.println(s);

        op = Person.employees.stream()
                .max(comparing(Person::salary));
        s = op.map(p -> p.name()+" has the greatest salary").orElse("no one");
        System.out.println(s);
    }
}
