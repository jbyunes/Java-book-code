package chapter.threads.asynchronous;

import java.util.concurrent.CompletableFuture;

public class ErrorPropagationExample {
    public static void main(String[] args) {
        var f = CompletableFuture.completedFuture(666)
            .thenRun(() -> System.out.println("stage 1"))
            .thenRun(() -> System.out.println("stage 2"))
            .thenRun(() -> { throw new RuntimeException("problem"); })
            .thenRun(() -> System.out.println("stage 3"))
            .thenRun(() -> System.out.println("stage 4"));
        f.join();
    }
}
