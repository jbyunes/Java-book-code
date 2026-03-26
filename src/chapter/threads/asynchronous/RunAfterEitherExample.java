package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;
import static chapter.threads.threads.Utils.*;
import java.util.function.Function;

public class RunAfterEitherExample {
    public static void main(String[] args) {
        var a = supplyAsync(() -> {
            delay(2000); return "Product from API A";
        });

        var b = supplyAsync(() -> {
            delay(3000); return "Product from API B";
        });

        a.runAfterEither(b, () -> {
            System.out.println("LOG: Item found");
        });
        
        delay(2000);
        var item = a.applyToEither(b, Function.identity());
        System.out.println("Received: " + item.join());
    }
}
