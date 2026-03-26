package chapter.threads.asynchronous;

import static java.util.function.BinaryOperator.*;
import static java.util.Comparator.*;
import java.util.concurrent.CompletableFuture;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;

import java.util.ArrayList;

public class ThenCombineExample {
    public static void main(String[] args) throws Exception {
        var futures = new ArrayList<CompletableFuture<Price>>();
        
        names.forEach(n -> futures.add(askForShoes(n)));
        
        var f = futures.get(0)
                .thenCombineAsync(futures.get(1),
                                  minBy(comparing(Price::price)))
                .thenCombineAsync(futures.get(2),
                                  minBy(comparing(Price::price)));
        var cheapest = f.join();
        print("Cheapest: "+cheapest);
    }
}
