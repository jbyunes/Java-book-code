package chapter.threads.asynchronous;

import static java.util.concurrent.CompletableFuture.*;
import java.util.function.Supplier;

import chapter.threads.threads.CityTemperature;

import static chapter.threads.threads.Utils.*;

public class ThenAcceptSimpleExample {
    public static Supplier<CityTemperature> getTemperature(String city) {
        return () -> new CallableWeatherTask("Paris").call();
    }
    public static void main(String[] args) {
        print("Creating a future");
        var f = supplyAsync(getTemperature("Paris"));
        print("Adding continuations");
        var f1 = f.thenAcceptAsync(e -> print(e));
        var f2 = f.thenAcceptAsync(e ->
            print(e.name()+": "+(e.temperature()>20?"Hot":"Cold")));
        
        print("Joining terminations");
        f1.join();
        f2.join();  
        print("---The end---");
    }
}
