package chapter.novelties.records;

import java.util.Iterator;

public record IntTuple(int... values) implements Iterable<Integer> {
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = 0;
            @Override public boolean hasNext() {
                return index < values.length;
            }
            @Override public Integer next() {
                return values[index++];
            }
        };
    }  
    @SafeVarargs
    public static IntTuple of(int... values) {
        return new IntTuple(values);
    }
}
