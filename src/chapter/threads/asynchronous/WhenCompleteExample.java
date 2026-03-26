package chapter.threads.asynchronous;


import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static chapter.threads.threads.Utils.*;
import chapter.threads.threads.Utils;
import static chapter.threads.asynchronous.ThenApplyExample.*;

public class WhenCompleteExample {
    private static CompletableFuture<Void>
    askFor(long start, String name) {
        var f = askForShoes(name);
        return f.orTimeout(3000, TimeUnit.MILLISECONDS)
                .whenComplete((v,e) -> {
                    if (e!=null) {
                        print(name+" not in time");
                    }
                })
                .thenAccept(Utils::print)
                .exceptionally(v -> null); // ensure no exception outside...

    }
    public static void main(String []args) {
        var futures = new ArrayList<CompletableFuture<Void>>();
        
        names.forEach(n -> futures.add(askFor(start,n)));
        
        futures.forEach(CompletableFuture::join);      
    }
}
