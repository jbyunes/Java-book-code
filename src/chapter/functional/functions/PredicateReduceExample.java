package chapter.functional.functions;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class PredicateReduceExample {
    @SuppressWarnings("unused")
    public static <T> T reduce(T zero, Iterable<T> i, BinaryOperator<T> o) {
        return reduce(zero, i, o, e -> true);
    }
    public static <T> T reduce(T zero, Iterable<T> i, BinaryOperator<T> o, Predicate<T> f) {       
        T r = zero;
        for (T e : i) { if (f.test(e)) { r = o.apply(r, e); } }
        return r;
    }
    public static void main(String[] args) {
        Predicate<Integer> isEven = e -> e%2==0;
        BinaryOperator<Integer> sum = (a,b) -> a+b;
        BinaryOperator<Integer> product = (a,b) -> a*b;
        List<Integer> l = List.of(5, 2, 3, 4, 1);
        System.out.println(reduce(0, l, sum));
        System.out.println(reduce(0, l, sum, isEven));
        System.out.println(reduce(1, l, product));
        System.out.println(reduce(1, l, product, isEven));
    }

}
