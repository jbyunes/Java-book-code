package chapter.threads.threads;

import java.util.ArrayList;

import static chapter.threads.threads.Utils.cities;
import static chapter.threads.threads.Utils.print;
import java.util.TreeMap;
import static java.util.Collections.*;

public class SynchronizingAdapterExample {
    public static void main(String[] args) {
        var map = new TreeMap<String,Integer>();
        var results = synchronizedMap(map); // thread safety...
        var l = new ArrayList<Thread>();
        print("Main");
        for (String city : cities) {
            var t = new Thread(new WeatherMapTask(city,results));
            l.add(t);
            t.start();
        }
        print("Main is free to continue");
        l.forEach(t -> {
            try { t.join(); } catch (InterruptedException e) {}
        });
        print("Main joined threads");
        print(results);
    }
}
