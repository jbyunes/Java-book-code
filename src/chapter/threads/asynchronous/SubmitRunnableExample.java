package chapter.threads.asynchronous;

import java.util.concurrent.Executors;

import chapter.threads.threads.WeatherTask;

import static chapter.threads.threads.Utils.*;

public class SubmitRunnableExample {
    public static void simple() {
        System.out.println("No shutdown");
        var es = Executors.newFixedThreadPool(2);
        cities.forEach(c -> es.submit(new WeatherTask(c)));
        print("end");
    }
    public static void withClose() {
        System.out.println("With close()");
        var es = Executors.newFixedThreadPool(2);
        cities.forEach(c -> es.submit(new WeatherTask(c)));
        print("before close");
        es.close();
        print("after close");
    }
    public static void withTry () {
        System.out.println("With try");
        try (var es = Executors.newFixedThreadPool(2)) {
            cities.forEach(c -> es.submit(new WeatherTask(c)));
        }
        print("after try");
    }
    public static void main(String[] args) {
//        simple();
//        System.out.println("==================");
//        withClose();
//        System.out.println("==================");
        withTry();
    }
}
