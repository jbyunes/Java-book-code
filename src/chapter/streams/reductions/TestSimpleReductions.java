package chapter.streams.reductions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import chapter.streams.utils.Person;

public class TestSimpleReductions {
    public static void main(String[] args) {
        int array[] = { 4, 5, 6 };
        int sum = Arrays.stream(array).reduce(0, Integer::sum);
        System.out.println(sum);

        int product = Arrays.stream(array).reduce(1, (a,v) -> a*v);
        System.out.println(product);

        boolean b = List.of(1, 10, 3).stream()
                .reduce(true, (a, e) -> a && e<10, Boolean::logicalAnd);
        System.out.println(b);


        var res = Stream.of("hello", "goodbye", "alpha", "blog")
                .reduce((s1,s2) -> s1.compareTo(s2) < 0 ? s1 : s2);
        System.out.println(res.orElseThrow());

        var total = Person.employees.stream()
                .reduce(0, (v,p) -> v+p.salary(), Integer::sum);
        System.out.println(total);

        var theSum = Stream.of( "1", "13", "foo", "59" )
                .reduce(0,
                        (a,s) -> {
                            try { return a + Integer.parseInt(s); }
                            catch(Throwable e) {}
                            return a;
                        }, 
                        Integer::sum);
        System.out.println(theSum);

        var aSum = Stream.of( "1", "13", "foo", "59" )
                .map(s -> {
                    try { return Optional.of(Integer.parseInt(s)); }
                    catch(Throwable e) {}
                    return Optional.<Integer>empty();
                }) 
                .filter(Optional::isPresent)
                .map(Optional::get)
                .mapToInt(x -> x)
                .reduce(0, Integer::sum);
        System.out.println(aSum);

        var aProduct = Stream.of( "1", "0", "foo", "59" )
                .reduce(1,
                        (a,s) -> {
                            if (a==0) return 0;
                            System.out.println(s+" parsed");
                            try { return a * Integer.parseInt(s); }
                            catch(Throwable e) {}
                            return a;
                        }, 
                        (v1,v2) -> v1*v2);
        System.out.println(aProduct);

        var r = Stream.iterate(0,i->i+1) .limit(100)
            .filter(i -> i%2==0)
            .reduce(new HashSet<Integer>(),
                    (s, i) -> { s.add(i); return s; },
                    (s1, s2) -> { s1.addAll(s2); return s1; });
        System.out.println(r);

        var r2 = Stream.iterate(0,i->i+1).limit(100)
            .filter(i -> i%2==0)
            .reduce(new HashSet<Integer>(),
                    (s, i) -> { var h = new HashSet<>(s);
                        h.add(i);
                        return h;
                    },
                    (s1, s2) -> { var h = new HashSet<>(s1); 
                        h.addAll(s2);
                        return h;
                    });
        System.out.println(r2);
    }
}
