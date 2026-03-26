package chapter.threads.parallel;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class ProductTaskExample {
    public static void main(String[] args) {
        final long N = 10_000_000L;
        Utils.printInfos();
        var r = new ProductTask(BigInteger.valueOf(N));
        var v = r.invoke();
        System.out.println(N+"! is " + v.bitLength()+" bits long.");
        System.out.println("Threads: " + ProductTask.threads.size());
        ProductTask.threads.forEach((k, v1) -> {
            System.out.println("Thread: " + k);
            v1.forEach(i -> System.out.println("\t" + i));
        });
        var c = ForkJoinPool.commonPool().getStealCount();
        System.out.println("Steal count is "+c);
    }
}
