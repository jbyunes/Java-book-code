package chapter.threads.threads;

import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class ConcurrentStructureExample {
    public static void main(String[] args) throws InterruptedException {
        var channel = new ArrayBlockingQueue<CityTemperature>(2);
        var threads = new ArrayList<Thread>();
        for (var city: cities) {
            var t = new Thread(new WeatherQueueTask(city,channel));
            threads.add(t);
            t.start();
        }
        for (int i=0; i<cities.size(); i++) {
            var r = channel.take();
            print(r);
        }
    }    
}
