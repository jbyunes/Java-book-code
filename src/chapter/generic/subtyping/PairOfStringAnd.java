package chapter.generic.subtyping;

public record PairOfStringAnd<T>(String s, T v) implements Pair<String,T>  {
    @Override public String getFirst() { return s; }
    @Override public T getSecond() { return v; }
}
