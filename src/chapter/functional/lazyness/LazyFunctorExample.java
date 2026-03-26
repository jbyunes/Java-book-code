package chapter.functional.lazyness;

public class LazyFunctorExample {
    public static int f(int i) {
        boolean b = true;
        while (b) {}
        return i;
    }
    public static void
    f(boolean b, LazyFunctor<Integer> x, LazyFunctor<Integer> y) {
        System.out.println(b?x.get():y.get());
    }
    public static void main(String[] args) {
        var arg1 = LazyFunctor.of(() -> 3)
            .map(LazyFunctorExample::f);
        var arg2 = LazyFunctor.of(() -> 4);
        f(false, arg1, arg2);
        System.out.println("End");
    }
}
