package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.print;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ExceptionnallyExample {
    public static void main(String[] args) throws Exception {
        var f = supplyAsync(() -> Web.fetch())
            .exceptionally(e -> {
                print(e.getMessage());
                return Cache.fetch();
            })
            .thenAccept(v -> print(v));
        f.join();
    }

}
