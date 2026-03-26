package chapter.generic.capture;

import java.util.List;

final public class Utils {
    private Utils() {};
    public static <T> void fill(List<T> l, T t) {
        l.add(t);
    }
    public static void rotate(List<?> l) {
//        l.addFirst(l.removeLast());
    }
    public static void test() {
        @SuppressWarnings("unused")
        List<?> l = null;
//        fill(l, new Object());
    }
}
