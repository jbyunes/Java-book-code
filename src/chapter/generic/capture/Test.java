package chapter.generic.capture;

import java.lang.reflect.Array;
import java.util.List;
import java.util.function.Supplier;

class E {
    static <T> int getHashCode(T e) {
        return e.hashCode();
    }
    static <T extends Runnable & Supplier<T>> T runAndGet(T e) {
        e.run();
        return e.get();
    }
    static <T extends Runnable & Supplier<T>> T runAndGet(List<? extends T> l) {
        var e = l.get(0);
        e.run();
        return e.get();
    }
    static <T extends Runnable & Supplier<T>> int what(List<? super T> l) {
        return l.get(0).hashCode();
    }
}


public class Test {
    public static void rotate(List<?> l) {
        rotateHelper(l);
    }
    private static <T> void rotateHelper(List<T> l) {
        l.addFirst(l.removeLast());
    }
    public static void m3(List<?> l) {
        m4(l.get(0));
    }
    public static <T> void m4(T t) {
        
    }
    @SuppressWarnings("unchecked")
    public static <T> T[]createArray(Class<T> c,int size) {
        return (T[])Array.newInstance(c, size);
    }
    public static void main(String[] args) {
        Number[] a = createArray(Integer.class,10);
        for (var i: a) {
            System.out.println(i);
        }
    }

}
