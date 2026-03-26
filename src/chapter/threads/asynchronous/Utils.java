package chapter.threads.asynchronous;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

//public class Utils {
//    public static final long start = System.currentTimeMillis();
//    public static final List<String> names = List.of("Azamon.biz", "Mute.shop", "CuttenRat.com");
//    public final static Random random = new Random();
//    public static void print(String msg) {
//        System.out.println("["+tid()+"]\t"+msg);
//    }    
//    public static void print(Object o) {
//        System.out.println(timeFrom(start)+" "+o);
//    }
//    public static String timeFrom(long start ) {
//        return (System.currentTimeMillis()-start)+"ms\t["+tid()+"]\t";
//    }
//    public static Long tid() {
//        return Thread.currentThread().threadId();
//    }
//    public static long delay(long ms) {
//        try { Thread.sleep(ms); } catch (Exception e) { }
//        return ms;
//    }
//    public static long delay() {
//        return delay(random.nextLong(1_000,3_000));
//    }
//}
