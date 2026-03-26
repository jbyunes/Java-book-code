package chapter.functional.curry;

import java.util.function.BiFunction;
import java.util.function.Function;

record Person(String firstName, String lastName) { }

public class CurryCTorExample {
    public static <T, U, R>
    BiFunction<U, T, R> swap(BiFunction<T, U, R> f) {
        return (u,t) -> f.apply(t,u);
    }
    public static <T, U, R>
    Function<T, Function<U, R>> curry(BiFunction<T, U, R> f) {
        return t -> u -> f.apply(t, u);
    }
    public static void main(String[] args) {
        var daltonFactory  = curry(swap(Person::new)).apply("Dalton");
        
        String [] fns = {"Joe", "Jack", "William", "Averell"};
        for (var fn: fns) {
            System.out.println(daltonFactory.apply(fn));
        }
    }
}
