package chapter.threads.asynchronous;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import static chapter.threads.threads.Utils.*;

public class ThenRunAsyncExample {
    public static void main(String[] args) {
        Function<Void,Integer> s = _ -> {
            print("function<v,i> tid");
            delay();
            return 666;
        };
        Consumer<String> c = st -> {
            print("consumer tid");
            delay();  
            System.out.println(st);
        };
        Function<Integer,String> f = v -> {
            print("function<i,i> tid");
            delay();  
            return "Value: "+v;
        };
        Runnable begin = () -> {
            print("begin");
        };
        Runnable end = () -> {
            print("end");
            delay();
        };
        var f1 = CompletableFuture.runAsync(begin)
            .thenApplyAsync(s)
            .thenApplyAsync(f)
            .thenAcceptAsync(c)
            .thenRunAsync(end);
        var f2 = CompletableFuture.runAsync(begin)
                .thenApplyAsync(s)
                .thenApplyAsync(f)
                .thenAcceptAsync(c)
                .thenRunAsync(end);
        var f3 = CompletableFuture.runAsync(begin)
                .thenApplyAsync(s)
                .thenApplyAsync(f)
                .thenAcceptAsync(c)
                .thenRunAsync(end);
        f1.join();
        f2.join();
        f3.join();
    }
}
