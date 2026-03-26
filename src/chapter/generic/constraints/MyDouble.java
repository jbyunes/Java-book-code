package chapter.generic.constraints;

public record MyDouble(double value) implements Comparable<MyInteger> {
    @Override
    public int compareTo(MyInteger i) {
        return Double.compare(value,i.value());
    }
}