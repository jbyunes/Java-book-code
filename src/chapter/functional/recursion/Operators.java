package chapter.functional.recursion;

import java.util.function.Function;

public class Operators {
    public static <T,U> Function<T,U> Z(Function<Function<T,U>,Function<T,U>> toRecurse) {
        interface Recursable<T,U> {
            Function<T,U> apply(Recursable<T,U> x);
        }
        Recursable<T,U> omega = x -> x.apply(x);
        Function<Function<Function<T,U>,Function<T,U>>,Function<T,U>> Z = r -> omega.apply(f -> r.apply(x -> omega.apply(f).apply(x)));
        return Z.apply(toRecurse);
    }   
}
