package chapter.streams.intro;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class Test {

    public static void main(String[] args) {
        // JBY

        Person.employees.stream()
            .sorted(comparing(Person::salary).reversed())
            .map(Person::name)
            .forEach(System.out::println);
        System.out.println();

        Person.employees.stream()
            .sorted(comparing(Person::salary))
            .map(Person::name)
            .map(String::toUpperCase)
            .forEach(System.out::println);
        System.out.println();

        //  var t = Stream.of(3,"Toto");

        IntStream.iterate(0, i -> i < 10, i -> i + 1)
            .filter(i -> i % 2 == 0)
            .forEach(System.out::println);

        IntStream.iterate(1899, y -> y < 2024, y -> y + 1)
            .filter(y -> y % 4 == 0)
            .filter(y -> !(y % 100 == 0) || y % 400 == 0)
            .forEach(System.out::println);

        IntStream.iterate(0, i -> i < 10, i -> i + 1)
            .filter(i -> i > 27)
            .forEach(System.out::println);

        {
            boolean b = List.of(1, 10, 3)
                    .stream()
                    .reduce(true, (a, e) -> a && e < 10, (r1, r2) -> r1 && r2);
            System.out.println("All match " + b);
            System.out.println();
        }

        // count

        {
            long n = List.of(1, 10, 3)
                    .stream()
                    .reduce(0, (count, e) -> count + 1);
            System.out.println("Il y a " + n + " éléments.");
        }

        {
            String str = "be bop a lula";
            System.out.println("****forEach without using parallel****");
            str.chars()
            .forEach(s -> System.out.print((char) s));

            System.out.println("\n****forEach with using parallel****");
            str.chars()
            .parallel()
            .forEach(s -> System.out.print((char) s));

            System.out.println("\n****forEachOrdered with using parallel****");
            str.chars()
            .parallel()
            .forEachOrdered(s -> System.out.print((char) s));
        }

        {
            System.out.println();
            int[] tableau = { 4, 5, 6 };
            int   somme   = Arrays.stream(tableau)
                    .reduce(0, Integer::sum);
            System.out.println(somme);
            somme = Arrays.stream(tableau)
                    .reduce(Integer::sum)
                    .orElse(0);
            System.out.println(somme);

            int produit = Arrays.stream(tableau)
                    .reduce(1, (a, v) -> a * v);
            System.out.println(produit);

            String[] strings = { "1", "13", "59" };
            somme = Arrays.stream(strings)
                    .reduce(0, (a, s) -> {
                        int v = a;
                        try {
                            v += Integer.parseInt(s);
                        } catch (Throwable e) {
                        }
                        ;
                        return v;
                    }, Integer::sum);
            System.out.println(somme);

        }

        // toArray
        System.out.println("---------toArray-------------");
        {
            int[] res = IntStream.iterate(0, i -> i+1)
                    .limit(10)
                    .filter(i -> i % 2 == 0)
                    .toArray();
            for (var e : res) {
                System.out.print(e+" ");
            }
            System.out.println();

            Object[] res2 = Stream.iterate(0, i -> i + 1)
                    .limit(10)
                    .filter(i -> i % 2 == 0)
                    .toArray();
            for (var e : res2) {
                System.out.print(e + " ");
            }
            System.out.println();

            {
                Integer[] resultat = Stream.iterate(0, i -> i + 1)
                        .limit(10)
                        .filter(i -> i % 2 == 0)
                        .toArray(Integer[]::new);
                for (var e : resultat) {
                    System.out.print(e + " ");
                }
                System.out.println();
            }

            System.out.println("---------toList-------------");
            List<String> l = Person.employees.stream()
                    .map(Person::name)
                    .filter(s -> s.startsWith("J"))
                    .toList();
            System.out.println(l);
        }

        {
            System.out.println("---------collect-------------");
            ArrayList<String> l = Person.employees.stream()
                    .map(Person::name)
                    .filter(s -> s.startsWith("J"))
                    .collect(ArrayList::new, Collection::add, Collection::addAll);
            System.out.println(l);

        }

        System.out.println("\n---------averaging--------");
        {
            double salaireMoyen = Person.employees.stream()
                    .collect(Collectors.averagingDouble(Person::salary));
            System.out.println(salaireMoyen);
        }
        {
            var salaireMoyen = Person.employees.stream()
                    .mapToDouble(Person::salary)
                    .average()
                    .orElse(0.);
            System.out.println(salaireMoyen);
        }

        System.out.println("\n---------collectAndThen--------");

        {
            List<String> list = Person.employees.stream()
                    .map(Person::name)
                    .collect(collectingAndThen(toList(), Collections::synchronizedList)); // ou immutableList
            System.out.println(list);
        }
        {
            List<String> list = Person.employees.stream()
                    .map(Person::name)
                    .collect(toUnmodifiableList()); // ou synchronizedList
            System.out.println(list);
        }
        {
            int totalSalaries = Person.employees.stream().map(Person::salaries).flatMap(Collection::stream).mapToInt(x->x).sum();
            Map<String, Integer> map = Person.employees.stream()
                    .collect(groupingBy(Person::name,
                                        collectingAndThen(flatMapping(p -> p.salaries().stream(),summingInt(e->e)),
                                                          e -> (int)(100.0*e/totalSalaries))));
            map.entrySet().forEach(e -> System.out.println(e.getKey()+"="+e.getValue()+"%"));
            System.out.println();
        }
        {
            int totalSalaries = Person.employees.stream()
                    .map(Person::salaries)
                    .flatMap(Collection::stream)
                    .mapToInt(x -> x)
                    .sum();
            Function<Integer,Integer> toPercent = e -> (int)(100.0*e/totalSalaries);
            Map<String, Integer> map = Person.employees.stream()
                    .collect(groupingBy(Person::name,
                                        collectingAndThen(flatMapping(p -> p.salaries().stream(),
                                                                      summingInt(e->e)),
                                                          toPercent)));
            map.entrySet().forEach(e -> System.out.println(e.getKey()+"="+e.getValue()+"%"));
            System.out.println();
        }
        /*
        {
            Map<Person, Double> map = employees.stream()
                .collect(groupingBy(Function.identity(),
                                    collectingAndThen(counting(), count -> 100. * count / employees.size())));
            map.entrySet()
                .forEach(e -> System.out.println(e.getKey().name()+"="+e.getValue()+"%"));
            System.out.println();
        }
         */

        System.out.println("\n---------filtering--------");
        {
            Predicate<Person> bigSalary = p -> p.salaries().stream().anyMatch(s -> s>=1500);
            var res = Person.employees.stream()
                    .collect(Collectors.groupingBy(Person::department,
                                                   filtering(bigSalary,
                                                             toList())));
            res.entrySet()
            .forEach(System.out::println);
            System.out.println();
        }


        System.out.println("\n---------toCollection--------");
        {
            ArrayList<Person> res = Person.employees.stream()
                    .filter(p -> p.salary()>1200)
                    .collect(toCollection(ArrayList::new));
            res.forEach(System.out::println);
        }

        // groupingBy
        System.out.println("\n---------groupingBy--------");

        {
            Map<String,List<Person>>  res = Person.employees.stream()
                    .collect(groupingBy(Person::department));
            res.entrySet()
            .forEach(System.out::println);
            System.out.println();
        }
        {
            Map<String,Set<Person>>  res = Person.employees.stream()
                    .collect(groupingBy(Person::department, toSet()));
            res.entrySet()
            .forEach(System.out::println);
            System.out.println();
        }
        {
            System.out.println();
            Map<String,Set<Person>> res = Person.employees.stream()
                    .collect(groupingBy(Person::department, TreeMap::new, toSet()));
            res.entrySet()
            .forEach(System.out::println);
        }

        // partitioningBy
        System.out.println("\n---------partitioningBy--------");
        {
            System.out.println();
            Map<Boolean,List<Person>> res = Person.employees.stream()
                    .collect(partitioningBy(p -> p.salary() > 1200));
            res.entrySet()
            .forEach(System.out::println);
        }
        {
            System.out.println();
            var res = Person.employees.stream()
                    .collect(partitioningBy(p -> p.salary() > 1200, toSet()));
            res.entrySet()
            .forEach(System.out::println);
        }

        // teeing
        {
            System.out.println("\n-----teeing-----");
            Map<String,List<Person>> res = Person.employees.stream()
                    .collect(groupingBy(Person::department,
                                        teeing(collectingAndThen(maxBy(comparing(Person::salary)),Optional::get),
                                               collectingAndThen(minBy(comparing(Person::salary)),Optional::get),
                                               List::of)));
            res.entrySet()
            .forEach(System.out::println);
        }

        {
            record MinMax(int min, int max) {
                public MinMax(Optional<Integer> a, Optional<Integer> b) { this(a.get(),b.get());}
            }
            Function<Person, Integer> extractSalary = Person::salary;
            System.out.println();
            Map<String,MinMax> res = Person.employees.stream()
                    .collect(groupingBy(Person::department,
                                        teeing(mapping(extractSalary, minBy(Integer::compare)),
                                               mapping(extractSalary, maxBy(Integer::compare)),
                                               MinMax::new)));
            res.entrySet()
            .forEach(System.out::println);
        }


        // flatMapping
        {
            System.out.println("\n-------flatMapping--------");
            int res = Person.employees.stream()
                    .collect(flatMapping(p -> p.salaries().stream(),
                                         summingInt(Number::intValue)));
            System.out.println(res);
        }
        {
            int res = Person.employees.stream()
                    .map(Person::salaries)
                    .flatMap(Collection::stream)
                    .mapToInt(Number::intValue)
                    .sum();
            System.out.println(res);
        }

        {
            Map<String,Integer> res = Person.employees.stream()
                    .collect(groupingBy(Person::name,
                                        flatMapping(p -> p.salaries()
                                                    .stream(), summingInt(Integer::valueOf))));
            System.out.println(res);
        }

        // mapping
        {
            System.out.println("\n-------Mapping--------");
            Map<Integer,Set<String>> res = Person.employees.stream()
                    .collect(groupingBy(Person::salary,
                                        mapping(Person::name,
                                                toSet())));
            System.out.println(res);
        }

        // maxBy
        {
            System.out.println("\n-------MaxBy--------");
            Optional<Person> res = Person.employees.stream()
                    .collect(maxBy(comparing(Person::salary)));
            System.out.println(res.map(Person::name)
                               .orElse("nobody"));
        }
        {
            Optional<Person> res = Person.employees.stream()
                    .max(comparing(Person::salary));
            System.out.println(res.map(Person::name)
                               .orElse("nobody"));
        }
        {
            Map<String,Integer> res = Person.employees.stream()
                    .collect(groupingBy(Person::name,
                                        collectingAndThen(flatMapping(p -> p.salaries().stream(),
                                                                      maxBy(Integer::compareTo)),
                                                          o -> o.orElse(0))));
            System.out.println(res);
        }


        // Map<Salary,Name>
        System.out.println("\n-------exercice--------");
        {
            record SP(int salary,Person person) {}
            Map<Integer,Set<String>> res = Person.employees.stream()
                    .flatMap(p -> p.salaries().stream().map(s->new SP(s,p)).toList().stream())
                    .collect(groupingBy(SP::salary,mapping(SP::person,mapping(Person::name,toSet()))))
                    ;
            System.out.println(res);
        }

        {
            Collector<Person,Map<Integer,Set<String>>,Map<Integer,Set<String>>> myCollector = new Collector<>() {
                private BiFunction<Set<String>,Set<String>,Set<String>> setMerger = (s1,s2) -> {
                    s1.addAll(s2);
                    return s1;
                };

                @Override
                public Supplier<Map<Integer, Set<String>>> supplier() {
                    return () -> {
                        var m = new HashMap<Integer, Set<String>>();
                        return m;
                    };
                }

                @Override
                public BiConsumer<Map<Integer, Set<String>>, Person> accumulator() {
                    return (m,p) -> {
                        var set = new HashSet<String>();
                        set.add(p.name());
                        p.salaries().forEach(s -> m.merge(s, set, setMerger));
                    };
                }

                @Override
                public BinaryOperator<Map<Integer, Set<String>>> combiner() {
                    return (m1,m2) -> { 
                        m1.entrySet().forEach(e -> m2.merge(e.getKey(), e.getValue(), setMerger));
                        return m2;
                    };
                }

                @Override
                public Function<Map<Integer, Set<String>>, Map<Integer, Set<String>>> finisher() {
                    return Function.identity();
                }

                @Override
                public Set<Characteristics> characteristics() {
                    return Set.of(Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
                }

            };
            Map<Integer,Set<String>> res = Person.employees.stream().parallel().collect(myCollector);
            System.out.println(res);
        }

        // summarizingInt
        System.out.println("\n-------toMap--------");
        {
            Map<String,Integer> res = Person.employees.stream()
                    .collect(toMap(Person::name,Person::salary));
            System.out.println(res);
        }

        {
            Map<Integer,String> res = Person.employees.stream()
                    .collect(toMap(Person::salary,Person::name,(o,n) -> o+","+n));
            System.out.println(res);
        }

        {
            Map<Integer,String> res = Person.employees.stream()
                    .collect(toMap(Person::salary, Person::name, (o,n) -> o+","+n, TreeMap::new));
            System.out.println(res);
        }
    }

}
