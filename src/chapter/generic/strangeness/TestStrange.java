package chapter.generic.strangeness;

interface I<T> {}
interface A<T> extends I<A<A<T>>>{}
abstract class X {
    abstract <T> T foo(T x, T y);

    void bar(A<Integer> x, A<String> y){
        foo(x, y);
    }
}
/*
 * A<Integer> --> I<A<A<Integer>>
 * A<A<Integer>> --> I<A<A<A<A<Integer>>>>>
 */

public class TestStrange {
    @SuppressWarnings("unused")
	public static void main(String[] args) {
        Integer i = 666;
        Comparable<Integer> ci = i;
        Comparable<? extends Integer> cei = i;
        Comparable<? extends Comparable<Integer>> ceci = i;
        Comparable<? extends Comparable<? extends Integer>> cecei = i;

        Comparable<? super Integer> csi = i;
        Comparable<? extends Comparable<? super Integer>> cecsi = i;
        Comparable<? extends Comparable<? extends Comparable<? super Integer>>> cececsi = i;
    }
}