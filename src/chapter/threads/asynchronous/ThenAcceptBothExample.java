package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;
import static chapter.threads.threads.Utils.*;

public class ThenAcceptBothExample {
    static record Person(String firstName, int age) {};
    public static void main(String[] args) throws Exception {
        var age = supplyAsync(() -> {
            delay(2000); return 42;
        });
        var name = supplyAsync(() -> {
            delay(3000);  return "Joe";
        });
        name.thenAcceptBothAsync(age,
            (n,a) -> print("[LOG] age: "+a+ " name:"+n));
        
        name.thenCombineAsync(age, Person::new)
            .thenAcceptAsync(v -> print(v+" created."))
            .thenRunAsync( () -> print("END"))
            .join();
    }
}
