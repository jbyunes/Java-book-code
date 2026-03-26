package chapter.functional.recursion;

public class MemoizerTest {
    public static void main(String[] args) {
        RecursiveFunction<Integer,Long> fibo =
            RecursiveFunction.of(n -> n<2,
                                 n -> (long)n,
                                 (n,f) -> f.apply(n-1)+f.apply(n-2));
        System.out.println(fibo.apply(44));
        
        var fiboMemo = Memoizer.memoize(fibo);
        System.out.println(fiboMemo.apply(44));
        for (int i=30; i<45; i++) {
            System.out.println(fiboMemo.apply(i));
        }
    }
}
