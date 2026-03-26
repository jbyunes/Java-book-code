package chapter.generic.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public final class Utils {
    private Utils() {}
//    public static String toString(Integer[] t) {
//        if (t==null) return "null";
//        var b = new StringBuilder("Array: [");
//        for (var e: t) {
//            b.append(e+", ");
//        }
//        b.append("]");
//        return b.toString();
//    }
    public static <T> String toString(T [] t) {
        if (t==null) { return "null"; }
        var sj = new StringJoiner(", ", "[", "]");
        for (var e: t) { sj.add(e.toString()); }
        return sj.toString();
    }
    public static String toString(Collection<?> list) {
        if (list==null) { return "null"; }
        var sj = new StringJoiner(" : ", "[", "]");
        for (var e: list) { sj.add(e.toString()); }
        return sj.toString();
    }
    public static List<Number> convert(Collection<? extends Number> l) {
        Objects.requireNonNull(l);
        List<Number> r = new ArrayList<>();
        for (var e : l) { r.add(e); }
        return r;
    }
    public static void fill(List<? super Double> l) {
        Objects.requireNonNull(l);
        l.add(Math.random());
        l.add(Math.random());
    }
    public static <T> void copy(List<? super T> dest,
            List<? extends T> src) {
        Objects.requireNonNull(dest);
        Objects.requireNonNull(src);
        dest.clear();
        for (var e : src) { dest.add(e); }
    }
    public static void rotate(List<?> l) {
        rotateHelper(l);
    }   
    private static <T> void rotateHelper(List<T> l) {
        T t = l.removeLast();
        l.addFirst(t);
    }
}
