package chapter.threads.asynchronous;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;
import static chapter.threads.asynchronous.ThenApplyExample.*;

public class AcceptEitherExample {
    public static void main(String []args) {
        var aza = askForShoes(names.get(0));
        var mut = askForShoes(names.get(1));
        
        var f = aza.acceptEitherAsync(mut, Utils::print);
        f.join();
    }
}
