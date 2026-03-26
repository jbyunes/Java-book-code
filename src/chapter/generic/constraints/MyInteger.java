package chapter.generic.constraints;

public record MyInteger(int value) implements Comparable<MyDouble> {
    @Override
    public int compareTo(MyDouble d) {
        return Double.compare(value, d.value());
    }
}
