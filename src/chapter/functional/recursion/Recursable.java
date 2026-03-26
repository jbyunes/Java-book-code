package chapter.functional.recursion;

import java.util.function.BiFunction;
import java.util.function.Function;

@FunctionalInterface
public interface Recursable<T,U> extends Function<T,U> {
    public U apply(T i, Recursable<T,U> f);    
    public default U apply(T n) { return this.apply(n,this); }
    public static <T,U> Function<T,U>
    of(BiFunction<T, Recursable<T,U>,U> g) {
        return (Recursable<T,U>)(n,f) -> g.apply(n, f);
    }
    public static <T,U> Function<T,U>
    of(Function<T,Function<Function<T,U>,U>> g) {
        return of((n,f) -> g.apply(n).apply(f)); // uncurry
    }
}