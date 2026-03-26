package chapter.functional.lazyness;

import java.util.Iterator;
import java.util.List;

public class Iterators {
    public static Iterator<Integer> create(int start, int end) {
        return new Iterator<>() {
            private int i = start;
            @Override public boolean hasNext() { return i<end; }
            @Override public Integer next() { return i++; }
        };
    }
    public static Iterator<Integer> create(int start) {
        return new Iterator<>() {
            private int i = start;
            @Override public boolean hasNext() { return true; }
            @Override public Integer next() { return i++; }
        };
    }
    public static <T> Iterator<T>
        concat(Iterator<? extends T> first, Iterator<? extends T> second) {
        return new Iterator<>() {
            private Iterator<? extends T> current = first;
            @Override
            public boolean hasNext() {
                if (current.hasNext()) { return true; }
                else if (current == first) {
                    current = second;
                    return current.hasNext();
                }
                return false;
            }
            @Override public T next() { return current.next(); }
        };
    }
    public static <T> Iterator<T>
        cachedIterator(List<T> cache, Iterator<? extends T> iterator) {
        return new Iterator<>() {
            private int i=0;
            @Override public boolean hasNext() {
                return i<cache.size() || iterator.hasNext();
            }
            @Override public T next() {
                if (i < cache.size()) { return cache.get(i++); }
                i++;
                T next = iterator.next();
                cache.add(next);
                return next;
            }
        };
    }
}
