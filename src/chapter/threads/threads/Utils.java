package chapter.threads.threads;

import static java.lang.System.getProperty;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public final class Utils {
    public static final long start = System.currentTimeMillis();
    public static final List<String> cities =
        List.of("Paris", "Tokyo", "Rio");
    public static final List<String> names =
            List.of("Azamon.biz", "Mute.shop", "CuttenRat.com");
    public final static Random random = new Random();
    public static void print(Object o) {
        System.out.println(timeFrom(start)+" "+o);
    }
    public static String timeFrom(long start ) {
        return (System.currentTimeMillis()-start)+"ms\t["+tid()+"]\t";
    }
    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static void delay(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) { }
    }
    public static long delay() {
        long d = random.nextLong(1_000,3_000);
        delay(d);
        return d;
    }
    public static long tid() {
        return Thread.currentThread().threadId();
    }
    public static void printInfos() {
        System.out.println(getProperty("os.name")
            +", "+getProperty("os.version")
            +", "+getProperty("os.arch"));
        System.out.println(System.getProperty("java.vendor")
            +", "+getProperty("java.version"));
        System.out.println("Available processing units: "
            +Runtime.getRuntime().availableProcessors());
        System.out.println("Thread pool size: "
            +ForkJoinPool.getCommonPoolParallelism());
    }

    public static void main(String[]a) {
        printInfos();
    }
}
