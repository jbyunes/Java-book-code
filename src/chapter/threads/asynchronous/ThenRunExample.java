package chapter.threads.asynchronous;


import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.Executors.*;

public class ThenRunExample {
    public static void main(String []args) {
        try (var uiThread = newSingleThreadExecutor()) {
            var ui = new UI();
            var futures = new ArrayList<CompletableFuture<Void>>();
            names.forEach(n -> futures.add(
                askForShoes(n).thenAcceptAsync(e -> ui.add(e))
                              .thenRunAsync(() -> ui.show(), uiThread)));

            print("---JOINING---");
            futures.forEach(CompletableFuture::join);
        }
        print("---END---");
    }
}
