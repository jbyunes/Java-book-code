package chapter.functional.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import chapter.functional.interfaces.Computable;

public final class Utils {
    private Utils() {}
    public static <T,U> Supplier<U> transform(Supplier<T> s, Function<T,U> f) {
        return () -> f.apply(s.get());
    }
    public static <T> Collection<T> create(int n, Supplier<T> s) {
        var r = new ArrayList<T>();
        for (int i=0; i<n; i++) { r.add(s.get()); }
        return r;
    }
    public static <T,U> void consumeSequence(int n, Supplier<T> s, Function<T,U> f, Consumer<U> c) {
        for (int i=0; i<n; i++) c.accept(f.apply(s.get()));
    }
    public static <T> void consumeSequence(int n, Supplier<T> s, Consumer<T> c) {
        consumeSequence(n, s, Function.identity(), c);
    }
    public static <T> void consumeSequence(int n, Supplier<T> s) {
        consumeSequence(n, s, e -> System.out.println(e));
    }
    public static <T,R> Function<T,R> combine(ToIntFunction<T> f1, IntFunction<R> f2) {
        return t -> f2.apply(f1.applyAsInt(t));
    }
    public static <T,U,R> Function<T,R> combine(Function<T,U> f1, Function<U,R> f2) {
        return t -> f2.apply(f1.apply(t));
    }
    public static <T,R> Function<T,R>
    combine(ToIntFunction<T> f1, IntUnaryOperator o, IntFunction<R> f2) {
        return t -> f2.apply(o.applyAsInt(f1.applyAsInt(t)));
    }
    public static void evaluate(Computable c, int x) {
        System.out.println(c.compute(x));
    }
}
