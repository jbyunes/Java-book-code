package chapter.functional.monads;

import java.util.function.Function;

public class BasicMonad<T> {
    private T value;
    
    private BasicMonad(T v) {
        value = v;
    }
    
    public static <U> BasicMonad<U> of(U v) {
        return new BasicMonad<>(v);
    }

    public <U> BasicMonad<U> map(Function<T, U> f) { // Functor
        return BasicMonad.of(f.apply(value));
    }
    
    public <U> BasicMonad<U> flatMap(Function<T, BasicMonad<U>> f) { // Monad
        return f.apply(value);
    }
    
    public T get() {
        return value;
    }

}
