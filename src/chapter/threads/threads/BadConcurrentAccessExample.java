package chapter.threads.threads;


import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;
import java.util.TreeMap;

public class BadConcurrentAccessExample {
    public static void main(String[] args) {
        // set WeatherServer FAST to true to experiment a bad behaviour
        var results = new TreeMap<String,Integer>();
        var l = new ArrayList<Thread>();
        print("Main");
        for (var city: cities) {
            var t = new Thread(new WeatherMapTask(city,results));
            l.add(t);
            t.start();
        }
        print("Main is waiting...");
        l.forEach(t -> {
            try { t.join(); } catch (InterruptedException e) {}
        });
        print("Main joined threads");
        print(results);
    }
}
