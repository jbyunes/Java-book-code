package chapter.threads.asynchronous;

import static java.util.concurrent.Executors.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import chapter.threads.threads.CityTemperature;

import static chapter.threads.threads.Utils.*;

public class CallableWeatherExample {
    public static Future<CityTemperature>
    request(String city,ExecutorService service) {
        print("Submitting "+city);
        return service.submit(new CallableWeatherTask(city));
    }
    public static void main(String[] args) throws Exception {
        try (var service = newFixedThreadPool(2)) {
            var tickets = cities.stream()
                .map(c -> request(c,service))
                .toList();
            for (var t: tickets) {
                print(t.get());
            }
        }
    }
}
