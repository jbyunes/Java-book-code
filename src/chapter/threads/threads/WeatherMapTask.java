package chapter.threads.threads;

import java.util.Map;
import static chapter.threads.threads.WeatherUtils.*;
import static chapter.threads.threads.WeatherServer.*;

public class WeatherMapTask extends WeatherTask {
    private Map<String, Integer> map;

    public WeatherMapTask(String city, Map<String,Integer> map) {
        super(city);
        this.map = map;
    } 
    private String pushTemperature(String server, String city) {
        try {
            String result = getTemperature(server, city);
            int temp = extractTemperature(city,result);
            map.put(city,temp);
            return result;
        }
        catch (Throwable e) {}
        throw new RuntimeException(city+":Error.");
    }
    @Override
    public  void run() {
        timeRun(LOCAL_URL, city, this::pushTemperature);
    }
}
