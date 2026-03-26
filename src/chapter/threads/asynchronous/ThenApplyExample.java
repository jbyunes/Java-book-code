package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.Executors.*;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;

public class ThenApplyExample {
    public static CompletableFuture<Price>
    askForShoes(String name) {
        return supplyAsync(() -> new Provider(name))
                .thenApplyAsync(Provider::getConnection)
                .thenApplyAsync(c -> c.getItemPrice("Shoes"));
    }
    public static void main(String []args) {
        try (var ui = newSingleThreadExecutor()) {
            var futures = new ArrayList<CompletableFuture<Void>>();

            names.forEach(n ->
            futures.add(askForShoes(n).thenAcceptAsync(Utils::print,ui)));

            futures.forEach(CompletableFuture::join);
        }
    }
}
