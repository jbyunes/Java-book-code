package chapter.functional.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

public class CollectionSupplierExample {
    @SafeVarargs public static <T> Collection<T> create(T...ts) {
        return create(()->new ArrayList<T>(), ts);
    }
    @SafeVarargs public static <T> Collection<T> create(Supplier<Collection<T>> s, T...ts) {
        var c = s.get();
        for (var i: ts) c.add(i);
        return c;
    }
    public static void main(String[] args) {
        var c = create(1, 2, 3, 4, 5);
        System.out.println(c);
        c = create(()->new HashSet<Integer>(), 1, 2, 2, 3, 4, 1);
        System.out.println(c);
    }
}
