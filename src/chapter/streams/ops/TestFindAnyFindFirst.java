package chapter.streams.ops;

import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class TestFindAnyFindFirst {
    public static void main(String[] args) {
        var op = Person.employees.stream()
            .filter(e -> e.salary()>1000)
            .findAny();
        var s = op.map(p -> "Got: "+p).orElse("no one");
        System.out.println(s);
        
        op = Stream.<Person>empty()
            .filter(e -> e.salary()>1000)
            .findFirst();
        s = op.map(p -> "Got: "+p).orElse("no one");
        System.out.println(s);
    }
}
