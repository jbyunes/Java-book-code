package chapter.functional.memoize;

import java.util.HashMap;
import java.util.function.Function;

import chapter.functional.recursion.Recursable;

public class MainMemoize {
    public static <T,U> Function<T,U> memoize(Function<T,U> f) {
        var m = new HashMap<T,U>();
        return t -> { if (!m.containsKey(t)) m.put(t,f.apply(t)); return m.get(t); };
    }   
    public static void main(String[] args) {
        Function<Integer,Long> fibonacci = Recursable.of(n -> f -> n<2 ? 1 : f.apply(n-1)+f.apply(n-2));
        var fiboMemo = memoize(fibonacci);
        
        for (int i=30; i<45; i++) {
            System.out.println("fib("+i+")="+fibonacci.apply(i));
        }
        System.out.println("fib("+44+")="+fiboMemo.apply(44));
        System.out.println("fib("+44+")="+fiboMemo.apply(44));
        for (int i=30; i<45; i++) {
            System.out.println("fib("+i+")="+fiboMemo.apply(i));
        }
    }
}
