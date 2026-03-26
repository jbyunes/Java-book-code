package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;

public class ApplyToEitherExample {
    public static Price applyTax(Price p) {
        return new Price((int)(p.price()*1.20),p.item(),p.provider());
    }
    public static void main(String []args) {
        var aza = askForShoes(names.get(0));
        var mut = askForShoes(names.get(1));
        
        var f = aza
            .applyToEitherAsync(mut, ApplyToEitherExample::applyTax) // tax
            .thenAccept(p -> print(p+" (taxed)"));
        f.join();
    }

}
