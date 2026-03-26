package chapter.functional.functions;

@FunctionalInterface
public interface TriConsumer<T,U,V> {
    public void accept(T t, U u, V v);
    public default TriConsumer<T,U,V> andThen(
        TriConsumer<? super T, ? super U, ? super V> after) {
        return (t,u,v) -> { accept(t,u,v); after.accept(t,u,v); };
    }
}
