package chapter.functional.functions;

import java.util.List;
import java.util.function.BinaryOperator;

import java.util.Comparator;

public class OperatorExample {
    public static <T> T reduce(T zero, Iterable<T> i, BinaryOperator<T> o) {       
        T r = zero;
        for (T e : i) { r = o.apply(r, e); }
        return r;
    }
    public static void main(String[] args) {
        List<Integer> l = List.of(5, 2, 3, 4, 1);
        System.out.println(reduce(0, l, (a,b) -> a+b));   
        System.out.println(reduce(Integer.MIN_VALUE, l, (a,b) -> a>b?a:b));
        System.out.println(reduce(1, l, (a,b) -> a*b));   

        Comparator<Integer> c = (i1,i2) -> Integer.compare(i1,i2);
        System.out.println(reduce(Integer.MIN_VALUE, l, BinaryOperator.maxBy(c)));
    }
}
