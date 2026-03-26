package chapter.functional.functions;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Builders {
    private Builders() {}
    public static <T,U> BiConsumer<T,U> build(Consumer<? super T> ct,
                                              Consumer<? super U> cu) {
        return (t,u) -> { ct.accept(t); cu.accept(u); };
    }
}