package chapter.functional.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PredicateExample {
    public static <T,U> void filterAndConsume(int n,
            Supplier<T> s,
            Function<T,U> e,
            Predicate<T> p,
            Consumer<U> c) {
        while (n-- != 0) {
            T t = s.get();
            if (p.test(t)) {
                c.accept(e.apply(t));
            }
        }
    }

    public static void main(String[] args) {
        Predicate<Integer> isEven = i -> (i%2) == 0;
        Predicate<Integer> isMultipleOf10 =  i -> (i%10)==0;
        filterAndConsume(10,
                         () -> (int)(Math.random()*100),
                         Function.identity(),
                         isEven.and(isMultipleOf10.negate()),
                         e -> System.out.println(e));
    }

}
