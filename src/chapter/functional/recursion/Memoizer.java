package chapter.functional.recursion;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Memoizer {
    private static <T,U> BiFunction<T,Function<T,U>,U>
    memoize(HashMap<T,U> m, BiFunction<T,Function<T,U>,U> f) {
//      return (n,h) -> {
//        if (!m.containsKey(n)) { var v = f.apply(n, h); m.computeIfAbsent(n, k -> v); }
//      return m.get(n);
//      };
        return (n,h) -> {
            if (!m.containsKey(n)) { m.put(n,f.apply(n,h)); }
            else { System.out.println("Memoized value used for " + n); }
            return m.get(n);
        };
    }
    private static <T,U> Function<T,U>
    memoize(HashMap<T,U> m, Function<T,U> f) {
        return n -> m.computeIfAbsent(n, f::apply);
    }
    public static <T,U> Function<T,U>
    memoize(RecursiveFunction<T,U> f) {
        var m = new HashMap<T,U>();
        return RecursiveFunction.of(f.getPredicate(),
                                    memoize(m,f.getBase()),
                                    memoize(m,f.getGeneral()));
    }
}
