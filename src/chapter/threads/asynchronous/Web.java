package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;

public class Web {
    public static String fetch() {
        print("Connecting...");
        delay();
        if (random.nextBoolean()) {
            throw new RuntimeException("Connection failed");
        }
        print("Fetching data from web");
        delay();
        return "Data from web";
    }
}
