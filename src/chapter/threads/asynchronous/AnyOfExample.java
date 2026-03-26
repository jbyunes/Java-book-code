package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;
import static java.util.concurrent.CompletableFuture.*;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class AnyOfExample{
    public static void main(String []args) {
        var futures = new ArrayList<CompletableFuture<Price>>();
        
        names.forEach(p -> futures.add(askForShoes(p)));
        
        var f = anyOf(futures.get(0),futures.get(1),futures.get(2));
        var s = (Price)(f.join());
        print(s.toString());
    }
}
