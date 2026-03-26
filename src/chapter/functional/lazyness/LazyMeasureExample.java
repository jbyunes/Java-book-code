package chapter.functional.lazyness;

import java.util.function.IntSupplier;
import static chapter.functional.lazyness.Utils.*;

public class LazyMeasureExample {
    public static int computeSomeInt(int v) { delay(500); return v; }
    public static int f(int a, int b) { return nextBoolean()?a:b; }
    public static int lazyF(IntSupplier a, IntSupplier b) {
        return (nextBoolean()?a:b).getAsInt();
    }
    public static void test() {
        for (int i=0; i<10; i++) {
            System.out.print(f(computeSomeInt(1),computeSomeInt(2))+", ");
        }
        System.out.println();
    }
    public static void lazyTest() {
        for (int i=0; i<10; i++) {
            System.out.print(lazyF(() -> computeSomeInt(1),
                                   () -> computeSomeInt(2))+", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        measure(LazyMeasureExample::test);
        measure(LazyMeasureExample::lazyTest);
    }
}
