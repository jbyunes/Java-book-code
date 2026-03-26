package chapter.streams.collects;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collector;

import chapter.streams.utils.Person;

import static java.util.stream.Collectors.*;

public class TestTeeing {
    private static void print(Map<?,List<Person>> d) {
        for (var e: d.entrySet()) {
            var s = e.getValue().stream()
                .map(Person::name)
                .collect(joining(", "));
            System.out.println(e.getKey()+": "+s);
        }
    }
    private static <T,T1,T2,T3> Collector<T,?, List<Map<?,List<T>>>> tee3(
            Collector<T, ?, Map<T1, List<T>>> collector1,
            Collector<T, ?, Map<T2, List<T>>> collector2,
            Collector<T, ?, Map<T3, List<T>>> collector3
            ) {
        return teeing(collector1,
                      teeing(collector2, collector3, List::of),
                      (l1, l2) -> List.of(l1, l2.get(0), l2.get(1)));
    }
    public static void main(String[] args) {
        int totalSalaries = Person.employees.stream()
            .map(Person::salaries)
            .flatMap(Collection::stream)
            .mapToInt(x -> x)
            .sum();
        Function<Integer,Integer> toPercent = e -> (int)(100.0*e/totalSalaries);
        var map = Person.employees.stream()
            .collect(groupingBy(Person::name,
                                collectingAndThen(flatMapping(p -> p.salaries().stream(),
                                                              summingInt(e->e)),
                                                  toPercent)));
        map.entrySet()
            .forEach(e -> System.out.println(e.getKey()+"="+e.getValue()+"%"));
            
        var r = Person.employees.stream()
            .collect(teeing(summingInt(p -> p.salaries().stream().collect(summingInt(x -> x))),
                            groupingBy(Person::name,flatMapping(p -> p.salaries().stream(),summingInt(x -> x))),
                            (total, d) -> d.entrySet().stream().collect(toMap(Entry::getKey,e -> (10000*e.getValue()/total)/100.0))
                        ));
        System.out.println(r);
        
        var res = Person.employees.stream()
            .collect(teeing(groupingBy(Person::department),
                            groupingBy(Person::salary),
                            List::of));
        print(res.get(0));
        print(res.get(1));
        
        var result = Person.employees.stream()
            .collect(tee3(groupingBy(Person::department),
                          groupingBy(Person::salary),
                          groupingBy(p -> p.name().charAt(0))));
        print(result.get(0));
        print(result.get(1));
        print(result.get(2));

        var result2 = Person.employees.stream()
                .collect(teeing(groupingBy(Person::department),
                                teeing(groupingBy(Person::salary),
                                       groupingBy(p -> p.name().charAt(0)),
                                       List::of),
                                (r1,r2) -> List.of(r1,r2.get(0),r2.get(1))));
        print(result2.get(0));
        print(result2.get(1));
        print(result2.get(2));

    }

}
