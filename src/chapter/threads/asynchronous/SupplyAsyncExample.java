package chapter.threads.asynchronous;

import java.util.concurrent.CompletableFuture;
import static chapter.threads.threads.Utils.*;
import java.util.function.Supplier;

public class SupplyAsyncExample {
    public static void main(String[] args) throws Exception {
        print("Main");
        Supplier<Integer> s = () -> {
            print("supplier");
            delay();
            int v = random.nextInt(100, 200);
            print("supplied value: "+v);
            return v;
        };
        var g = CompletableFuture.supplyAsync(s);
        var value = g.get();
        print("Got value: "+value);
    }
}
