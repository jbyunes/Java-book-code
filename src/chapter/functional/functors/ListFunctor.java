package chapter.functional.functors;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class ListFunctor<T> {
    private final T[] elements;
    @SafeVarargs private ListFunctor(T... values) { elements = values; }
    @SafeVarargs public static <T> ListFunctor<T> of(T... a) {
        return new ListFunctor<T>(a);
    }
    @SuppressWarnings("unchecked")
    public <R> ListFunctor<R> map(Function<? super T, R> f) {
        var result = new ArrayList<R>(elements.length);
        for (var e: elements) { result.add(f.apply(e)); }
        return new ListFunctor<>((R[])result.toArray());
    }
    @Override public String toString() {
        var sj = new StringJoiner(", ", "[", "]");
        for (var e : elements) { sj.add(e.toString()); }
        return sj.toString();
    }
    public void forEach(Consumer<? super T> c) {
        for (var e : elements) { c.accept(e); }
    }
    public T foldLeft(T zero, BiFunction<? super T,? super T,T> f) {
        T r = zero;
        for (var e : elements) { r = f.apply(r, e); }
        return r;
    }
}