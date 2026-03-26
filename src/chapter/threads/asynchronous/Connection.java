package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;

import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.CompletableFuture.*;

public record Connection(Provider provider) {
    public Price getItemPrice(String item) {
        var r = random.nextInt(1000,2000);
        print("("+provider+" will get price in "+r+"ms)");
        delay(r);
        var price = random.nextInt(10,20);
        print("("+provider+" got price $"+price+")");
        return new Price(price,item, provider);
    }
    public CompletableFuture<Price> getItemPriceAsync(String itemName) {
        return supplyAsync(() -> getItemPrice(itemName));
    }
}
