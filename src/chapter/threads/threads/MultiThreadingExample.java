package chapter.threads.threads;

import static chapter.threads.threads.Utils.*;

public class MultiThreadingExample {
    public static void main(String[] args) {
        print("Main");
        for (var city : cities) {
            var t = new Thread(new WeatherTask(city));
            t.start();
        }
        print("Main is free to continue");
    }
}