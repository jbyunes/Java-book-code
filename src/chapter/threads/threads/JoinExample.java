package chapter.threads.threads;

import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;

public class JoinExample {
    public static void main(String[] args) {
        var l = new ArrayList<Thread>();
        print("Main");
        for (String city : cities) {
            var t = new Thread(new WeatherTask(city));
            l.add(t);
            t.start();
        }
        print("Main is free to continue");
        l.forEach(t -> {
            try { t.join(); } catch (InterruptedException e) {}
        });
        print("Main joined threads");
    }
}
