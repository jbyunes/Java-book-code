package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;

public class Cache {
    public static String fetch() {
        print("Falling back to cache");
        delay();
        if (random.nextBoolean()) {
            throw new RuntimeException("No data in cache");
        }
        return "Data restored from cache";
    }      
}
