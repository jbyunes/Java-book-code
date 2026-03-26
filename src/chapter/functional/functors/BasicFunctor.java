package chapter.functional.functors;

import java.util.function.Function;

public class BasicFunctor<T> {
    private final T value;
    private BasicFunctor(T value) {
        this.value = value;
    }   
    public static <U> BasicFunctor<U> of(U v) {
        return new BasicFunctor<>(v);
    }
    public <U> BasicFunctor<U> map(Function<? super T,U> f) {
        return BasicFunctor.of(f.apply(value));
    }    
    public String toString() {
        return value.toString();
    }
    public T get() { return value; }
}
