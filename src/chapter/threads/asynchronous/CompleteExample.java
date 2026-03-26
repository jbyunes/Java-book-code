package chapter.threads.asynchronous;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;

import java.util.concurrent.CompletableFuture;

public class CompleteExample {
    public static void
    getValueFromAsync(String service, CompletableFuture<String> promise) {
        promise.completeAsync(() -> {
            print(service+" is searching a value");
            delay();
            print(service+" found one");
            return "Value from "+service;
        });
    }
    public static void main(String []args) {
        var f = new CompletableFuture<String>();
        getValueFromAsync("Gogol", f);
        getValueFromAsync("Bong", f);
        f.thenAccept(Utils::print).join();
    }
}
