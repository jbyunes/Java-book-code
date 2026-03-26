package chapter.functional.curry;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CurryExample {
    public static <T, U, R>
    Function<T, Function<U, R>> curry(BiFunction<T, U, R> f) {
        return t -> u -> f.apply(t, u);
    }
    public static <T, U, R>
    BiFunction<T,U,R> uncurry(Function<T, Function<U, R>> f) {
        return (t, u) -> f.apply(t).apply(u);
    }
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> sum = Integer::sum;
        var curriedAdder = curry(sum);
        System.out.println(curriedAdder.apply(1).apply(2));
        var incrementer = curry(sum).apply(1);
        System.out.println(incrementer.apply(5));
        var uncurriedSummer = uncurry(curriedAdder);
        System.out.println(uncurriedSummer.apply(1,2));
    }

}
