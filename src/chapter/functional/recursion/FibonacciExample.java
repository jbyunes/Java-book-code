package chapter.functional.recursion;

import java.util.function.Function;

public class FibonacciExample {
    public static void main(String[] args) {
        Function<Integer,Long> fibonacci =
            Recursable.of(n -> f -> n<2 ? 1 : f.apply(n-1)+f.apply(n-2));
        for (int i=0; i<10; i++) {
            System.out.println("fib("+i+")="+fibonacci.apply(i));
        }
    }
}
