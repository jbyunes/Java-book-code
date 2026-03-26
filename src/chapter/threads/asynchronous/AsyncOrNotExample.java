package chapter.threads.asynchronous;

import java.util.concurrent.CompletableFuture;

import static chapter.threads.threads.Utils.*;

public class AsyncOrNotExample {
    private static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> {
            print("supply"); delay(1000); return 666;
        });
    }
    public static void noAsync1() {
        var f = create();
        print("Before completions");
        var f1 = f.thenRun(() -> { delay(1000); print("run 1"); });
        var f2 = f.thenRun(() -> { delay(1000); print("run 2"); });
        print("After completions");
        f1.join(); f2.join();
    }
    public static void noAsync2() {
        var f = create();
        delay(2000); // ensure continuations after completion
        print("Before completions");
        var f1 = f.thenRun(() -> { delay(1000); print("run 1"); });
        print("In between");
        var f2 = f.thenRun(() -> { delay(1000); print("run 2"); });
        print("After completions");
        f1.join(); f2.join();
    }
    public static void async() {
        var f = create();
        print("Before completions");
        var f1 = f.thenRunAsync(() -> { delay(1000); print("run 1"); });
        var f2 = f.thenRunAsync(() -> { delay(1000); print("run 2"); });
        print("After completions");
        f1.join(); f2.join();
    }
    public static void main(String[] args) {
        print("Main");
//        noAsync1();
//        noAsync2();
        async();
    }
}
