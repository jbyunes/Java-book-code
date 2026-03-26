package chapter.threads.asynchronous;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompleteOnTimeoutExample {
    private static Map<String,Integer> cache =
            Map.of("Azamon.biz",22,
                   "CuttenRat.com", 23,
                   "Mute.shop", 25);   
    public static CompletableFuture<Price>
    askForShoes(String name) {
        var provider = new Provider(name);
        var f = CompletableFuture.completedFuture(provider)
                .thenApplyAsync(Provider::getConnection)
                .thenApplyAsync(c -> c.getItemPrice("Shoes"));
        f.completeOnTimeout(new Price(cache.get(name), "Shoes", provider),
                            3000, TimeUnit.MILLISECONDS);
        return f;
    }

    public static void main(String []args) {
        var futures = new ArrayList<CompletableFuture<Void>>();        
        names.forEach(p -> futures.add(
            askForShoes(p).thenAccept(Utils::print)));
        
        futures.forEach(CompletableFuture::join);
    }

}
