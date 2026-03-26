package chapter.functional.recursion;

@FunctionalInterface
public interface BasicRecursable<T,U> {
    public U apply(T i, BasicRecursable<T,U> f);
}
