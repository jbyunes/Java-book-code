package chapter.generic.subtyping;

public record UniformPair<T>(T first, T second) implements Pair<T, T> {
    @Override public T getFirst() { return first; }
    @Override public T getSecond() { return second; }
}