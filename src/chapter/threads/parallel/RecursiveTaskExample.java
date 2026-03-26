package chapter.threads.parallel;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskExample {
    public static void main(String[] args) {
        Utils.printInfos();
        var r = new RecursiveSummer(BigInteger.valueOf(25_000_000L));
        var v = r.invoke();
        System.out.println("Sum: " + v);
        System.out.println("Threads: " + RecursiveSummer.threads.size());
        RecursiveSummer.threads.forEach((k, v1) -> {
            System.out.println("Thread: " + k);
            v1.forEach(i -> System.out.println("\t" + i));
        });
        var c = ForkJoinPool.commonPool().getStealCount();
        System.out.println("Steal count is "+c);
    }

}
