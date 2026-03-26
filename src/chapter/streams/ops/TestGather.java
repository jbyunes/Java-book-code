package chapter.streams.ops;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

import static java.util.stream.Gatherers.*;

public class TestGather {
    public static void main(String[] args) throws Exception {
        try (var st = Files.lines(Paths.get("matrix3x3.txt"))) {
            var m = st
                .filter(s -> !s.startsWith("#"))
                .map(Integer::parseInt)
//                .gather(windowFixed(3).andThen(windowFixed(3)))
                .gather(windowFixed(3))
                .gather(windowFixed(3))
                .toList();
            m.forEach(System.out::println);
        }
        
        
        Stream.of(111,2,33,4,5,6,7,8,9)
            .gather(fold(TreeSet::new, (t, e) -> {
                if (t.size()<3) {
                    t.add(e);
                    return t;
                }
                t.add(e);
                t.remove(t.first());
                return t;
            }))
            .flatMap(Collection::stream)
            .forEach(System.out::println);
        
        
        var joe = Person.employees.get(0);
        System.out.println(joe);
        joe.salaries().stream()
            .gather(windowSliding(2))
            .filter(l -> l.get(1)>l.get(0)+1000)
            .forEach(System.out::println);
        var r = joe.salaries().stream()
            .gather(windowSliding(2))
            .anyMatch(l -> l.get(1)>l.get(0)+1000);
        System.out.println(r);
        
        var cumulatedSalaries = joe.salaries().stream()
            .gather(Gatherers.scan(() -> 0, Integer::sum))
            .toList();
        System.out.println(cumulatedSalaries);
        
        record NameAndSalary(String name, int totalSalary) {};
        Person.employees.stream()
            .flatMap(p -> p.salaries().stream()
                 .gather(fold(()->0, Integer::sum))
                 .map(s -> new NameAndSalary(p.name(),s)))
            .forEach(System.out::println);
                    
        Person.employees.stream()
            .flatMap(p -> p.salaries().stream()
                .gather(fold(()->0, Integer::sum))
                .map(s -> p.name()+" got "+(s>4000?"a fortune":"some bucks")))
            .forEach(System.out::println);

        Person.employees.stream()
            .flatMap(p -> p.salaries().stream()
                .gather(fold(()->0, Integer::sum))
                .map(s -> new NameAndSalary(p.name(),s)))
            .filter(ns -> ns.totalSalary()>4000)
            .forEach(System.out::println);
    }
    public static void old() {
        var list = new ArrayList<Integer>();
        for (int i=0; i<100; i++) list.add(i);
        var m = new HashMap<String,List<Integer>>();
        list.stream()
            .parallel()
            .map(i -> i*10)
            .map(i -> i/10)
            .peek(e -> {
                synchronized(m) {
                var n = Thread.currentThread().getName();
                var l = m.get(n);
                if (l==null) {
                    l = new ArrayList<Integer>();
                    m.put(n, l);
                }
                l.add(e);
                }})
            .gather(windowFixed(3))
//            .collect(Collectors.toList());
        .forEach(System.out::println);
//        System.out.println(r);
        m.forEach((t,es) -> System.out.println(t+" "+es));
    }

}
