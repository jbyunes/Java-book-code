package chapter.threads.threads;

import java.util.concurrent.BlockingQueue;
import static chapter.threads.threads.WeatherUtils.*;
import static chapter.threads.threads.WeatherServer.*;

public class WeatherQueueTask extends WeatherTask {
    private BlockingQueue<CityTemperature> queue;
    public WeatherQueueTask(String city,
            BlockingQueue<CityTemperature> queue) {
        super(city);
        this.queue = queue;
    } 
    private String putTemperature(String server, String city) {
        try {
            String result = getTemperature(server, city);
            int temp = extractTemperature(city,result);
            queue.put(new CityTemperature(city,temp));
            return result;
        }
        catch (Throwable e) {}
        throw new RuntimeException(city+" can't find temperature");
    }
    @Override
    public  void run() {
        timeRun(LOCAL_URL, city, this::putTemperature);
    }

}