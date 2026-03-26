package chapter.generic.constraints;

public class Cross {
    public static
    <T extends Comparable<S>, S extends Comparable<T>>
    void test(T t, S s) {
        System.out.println(t.compareTo(s));
        System.out.println(s.compareTo(t));
    }
}
