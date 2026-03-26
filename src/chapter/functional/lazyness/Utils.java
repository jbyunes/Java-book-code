package chapter.functional.lazyness;

import java.util.Random;

public class Utils {
    private static Random random = new Random();
    public static void delay(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) { }
    }
    public static long delay() {
        long d = random.nextLong(1_000,3_000);
        delay(d);
        return d;
    }
    public static boolean nextBoolean() {
        return random.nextBoolean();
    }
    public static int nextInt(int low, int high) {
        return random.nextInt(low,high);
    }
    public static void measure(Runnable f) {
        long start = System.currentTimeMillis();
        f.run();
        long end = System.currentTimeMillis();
        long secs = (end-start)/1000;
        System.out.println("Time = "+secs+"s.");
    }
}