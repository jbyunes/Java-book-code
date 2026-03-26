package chapter.functional.recursion;

import java.util.function.Function;

@FunctionalInterface
public interface RecursableAsFunction<T,U>
extends BasicRecursable<T,U>, Function<T,U> {
    default U apply(T n) {
        return this.apply(n,this);
    }
}
