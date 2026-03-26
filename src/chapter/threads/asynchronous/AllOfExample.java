package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;

import java.util.ArrayList;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.Executors.*;

public class AllOfExample {
    public static void main(String []args) {
        try (var uiThread = newSingleThreadExecutor()) {
            var ui = new UI();
            var futures = new ArrayList<CompletableFuture<Void>>();

            names.forEach(c -> futures.add(askForShoes(c).thenAccept(ui::add)));
            
            var f = allOf(futures.get(0), futures.get(1), futures.get(2));
            f = f.thenRunAsync(ui::show, uiThread);

            print("---JOINING---");
            f.join();
        }
        print("---END---");
    }
}
