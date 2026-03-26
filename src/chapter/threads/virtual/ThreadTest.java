package chapter.threads.virtual;

import static chapter.threads.threads.Utils.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest {
    public static void executeTasks(Executor executor) {
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                print("starts and blocks...");
                delay(5000); 
                print("ended.");
            });
        }
        executor.execute(() -> {
            print("Critical task !");
        });
    }

    public static void main(String[] args) {
//        System.out.println("--- Platform Threads ---");
//        try (var executor = Executors.newFixedThreadPool(1000)) { 
//            executeTasks(executor);
//        }

        System.out.println("--- Virtual Threads ---");
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executeTasks(executor);
        }
    }
}
