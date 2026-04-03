package chapter.threads.asynchronous;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class OrTimeoutExample {
    private static CompletableFuture<Price>
    askFor(String name) {
        var f = askForShoes(name);
        return f.orTimeout(3000, TimeUnit.MILLISECONDS);
    }
    @SuppressWarnings("unused")
	public static void main(String []args) {
        var futures = new ArrayList<CompletableFuture<Void>>();
        names.forEach(p -> futures.add(askFor(p)
            .thenAccept(Utils::print)));
        
        futures.forEach(f -> {
//            f.join();
            try {
                f.join();
            } catch(Throwable e) {
                switch(e.getCause()) {
                    case null -> e.printStackTrace();
                    case TimeoutException ex -> print("A service was not in time");
                    default -> e.printStackTrace();
                }
            }
        });
    }
}
