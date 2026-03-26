package chapter.functional.monads;

import java.util.Objects;
import java.util.function.Function;


/*
 * Is a Monad!
 */
public class OptionalMonad<T> {
    private final T value;
    private OptionalMonad(T value) {
        this.value = value;
    }
    public static <T> OptionalMonad<T> of(T a) {
        Objects.requireNonNull(a);
        return new OptionalMonad<T>(a);
    }
    public static <T> OptionalMonad<T> empty() {
        return new OptionalMonad<T>(null);
    }
    public <R> OptionalMonad<R> map(Function<? super T,R> f) {
        if (value==null) return empty();
        try { return of(f.apply(value)); }
        catch (Throwable e) { return empty(); }
    }
    public <U> OptionalMonad<U> flatMap(Function<? super T, OptionalMonad<U>> f) {
        if (value==null) return empty();
        try { return f.apply(value); }
        catch (Throwable e) { return empty(); }
    }  
    public T getOrElse(T other) {
        return value==null ? other : value;
    }
}