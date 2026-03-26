package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;
import static chapter.threads.threads.Utils.*;
import chapter.threads.threads.Utils;

public class HandleExample {
    public static void main(String[] args) throws Exception {
        var f = supplyAsync(Web::fetch)
            .handleAsync((v,e) -> {
                if (e==null) return v;
                print(e.getMessage());
                return Cache.fetch();
            })
            .thenAccept(Utils::print);
        f.join();
    }
}
