package chapter.streams.utils;

import java.util.function.Consumer;

public record Tree<T>(T value, Tree<? extends T> left,
                     Tree<? extends T> right) {
    public static <T> Tree<T> of(T value) {
        return new Tree<>(value, null, null);
    }
    public static <T> Tree<T> of(T value,
                                 Tree<? extends T> left,
                                 Tree<? extends T> right) {
        return new Tree<>(value, left, right);
    }
    public void expand(Consumer<? super T> c ) {
        if (left!=null) left.expand(c);
        c.accept(value);
        if (right!=null) right.expand(c);
    }
}