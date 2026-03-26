package chapter.functional.lazyness;

import java.util.function.Function;
import java.util.function.Supplier;

public class LazyFunctor<T> {
    private final Supplier<T> value;
    private LazyFunctor(Supplier<T> value) { this.value = value; }  
    public static <U> LazyFunctor<U> of(Supplier<U> s) {
        return new LazyFunctor<>(s);
    }
    public <U> LazyFunctor<U> map(Function<T,U> f) {
        return new LazyFunctor<>( () -> f.apply(value.get()) );
    }   
    public T get() { return value.get(); }
}
