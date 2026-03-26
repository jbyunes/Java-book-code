package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;

public class ThenComposeExample {
    private static CompletableFuture<Price>
    askForShoes(String name) {
        return completedFuture(name)
                .thenApplyAsync(Provider::new)
                .thenComposeAsync(Provider::getConnectionAsync)
                .thenComposeAsync(c -> c.getItemPriceAsync("Shoes"));
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
