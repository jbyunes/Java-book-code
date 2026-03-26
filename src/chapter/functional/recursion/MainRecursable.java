package chapter.functional.recursion;

import java.util.function.Function;

public class MainRecursable {
    public static void main(String args[]) {
        BasicRecursable<Integer,Long> r =
            (n,f) -> n==0 ? 1L : n*f.apply(n-1,f);
        System.out.println(r.apply(10,r));
        
        RecursableAsFunction<Integer,Long> s = (n,f) -> n==0 ? 1L : n*f.apply(n-1,f);
        System.out.println(s.apply(10));
        
        Function<Integer,Long> fact = Recursable.of((n,f) -> n==0 ? 1L : n*f.apply(n-1,f));
        System.out.println("fact(10)="+fact.apply(10));
        
        Function<Integer,Long> fac = Recursable.of(n -> f -> n==0 ? 1L : n*f.apply(n-1));
        System.out.println("fact(10)="+fac.apply(10));
        
        Function<Integer,Long> fibonacci = Recursable.of(n -> f -> n<2 ? n : f.apply(n-1)+f.apply(n-2));
        for (int i=0; i<10; i++) System.out.println("fib("+i+")="+fibonacci.apply(i));

        Function<Integer,Long> ff = Operators.Z( f -> n -> n==0 ? 1L : n*f.apply(n-1) );
        System.out.println(ff.apply(10));
    }
}
