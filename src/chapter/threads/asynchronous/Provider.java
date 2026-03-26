package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;

import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.CompletableFuture.*;

public record Provider(String name) {
    public Connection getConnection() {
        print("Getting connection to "+name);
        var r = random.nextInt(1000,2000);
        print("("+name+" will connect in "+r+"ms)");
        delay(r);
        print("Connected to "+name);
        return new Connection(this);
    }
    public CompletableFuture<Connection> getConnectionAsync() {
        return supplyAsync(this::getConnection);
    }
    @Override public String toString() {
        return name;
    }
}
