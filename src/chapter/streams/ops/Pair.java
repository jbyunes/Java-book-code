package chapter.streams.ops;

public record Pair(int e1, int e2) {
    @Override
    public boolean equals(Object o) {
        return o instanceof Pair p && p.hashCode() == this.hashCode();
    }
    @Override
    public int hashCode() {
        return this.e1 + this.e2;
    }
}
