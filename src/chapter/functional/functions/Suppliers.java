package chapter.functional.functions;

import java.util.function.Function;
import java.util.function.Supplier;

public class Suppliers {
    private Suppliers() {}
    public static <T,U> Supplier<U> makeFrom(Supplier<T> s, Function<T,U> f) {
        return new Supplier<U>() {
            @Override public U get() { return f.apply(s.get()); }
        };
    }
}
