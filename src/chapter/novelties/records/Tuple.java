package chapter.novelties.records;

import java.util.Arrays;
import java.util.Iterator;

public record Tuple<T>(T... values) implements Iterable<T> {
    @Override public Iterator<T> iterator() {
        return Arrays.asList(values).iterator();
    }  
//    public static <E> Tuple<E> of(E... values) {
//        return new Tuple<>(values);
//    }
}
