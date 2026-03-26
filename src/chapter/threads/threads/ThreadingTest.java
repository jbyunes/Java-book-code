package chapter.threads.threads;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ThreadingTest {

    public static void main(String[] args) {
        ThreadFactory tFact = r -> {
            System.out.println("Thread created");
            return new Thread(r);
        };
        try (var service = Executors.newFixedThreadPool(2, tFact)) {
            final var r = new Random();
            var futures = new ArrayList<Future<Integer>>();
            Callable<Integer> c = () -> {
                var v = r.nextInt(100, 1000);
                System.out.println(Thread.currentThread().threadId()+ " sleeping for "+v);
                try {
                    Thread.sleep(v);
                } catch(Throwable t) {}
                System.out.println(Thread.currentThread().threadId()+ " ended");
                return v;
            };
            for (int i=0; i<100; i++) {
                var f = service.submit(c);
                futures.add(f);
            }
            for (int i=0; i<100; i++) {
                try {
                    futures.get(i).get();
                } catch (Throwable e) {}
            }
        }

    }

}
