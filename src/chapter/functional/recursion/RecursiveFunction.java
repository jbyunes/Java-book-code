package chapter.functional.recursion;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class RecursiveFunction<T,U> implements Function<T,U> {
    private Predicate<T> p;
    private Function<T,U> b;
    private BiFunction<T,Function<T,U>,U> g;
    private Function<T,U> f;
    private RecursiveFunction(
            Predicate<T> p,
            Function<T,U> b,
            BiFunction<T,Function<T,U>,U> g) {
        this.p = p;
        this.b = b;
        this.g = g;
        f = Recursable.of(n -> f -> p.test(n) ? b.apply(n) : g.apply(n, f));
    }
    public static <T,U> RecursiveFunction<T,U> of(
            Predicate<T> p,
            Function<T,U> b,
            BiFunction<T,Function<T,U>,U> g) {
        return new RecursiveFunction<>(p, b, g);
    }
    @Override
    public U apply(T n) { return f.apply(n); }
    public Predicate<T> getPredicate() { return p; }
    public Function<T,U> getBase() { return b; }
    public BiFunction<T,Function<T,U> ,U> getGeneral() {
        return g;
    }
}
