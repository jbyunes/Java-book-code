package chapter.functional.monads;

import java.util.Objects;
import java.util.function.Function;

public class OptionalRevivableMonad<T> {
    private final T value;
    private OptionalRevivableMonad(T value) {
        this.value = value;
    }
    public static <T> OptionalRevivableMonad<T> of(T a) {
        Objects.requireNonNull(a);
        return new OptionalRevivableMonad<T>(a);
    }
    public static <T> OptionalRevivableMonad<T> empty() {
        return new OptionalRevivableMonad<T>(null);
    }
    public <R> OptionalRevivableMonad<R> map(Function<T,R> f) {
        return value == null ? empty() : of(f.apply(value));
    }
    public <U> OptionalRevivableMonad<U> flatMap(Function<T, OptionalRevivableMonad<U>> f) {
        return value == null ? empty() : f.apply(value);
    }   
    public OptionalRevivableMonad<T> revive(T v) {
        Objects.requireNonNull(v);
        return value==null?OptionalRevivableMonad.of(v):this;
    }
    public T getOrElse(T other) {
        return value==null ? other : value;
    }
}
