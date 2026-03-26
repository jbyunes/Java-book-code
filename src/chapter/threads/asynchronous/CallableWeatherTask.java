package chapter.threads.asynchronous;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.threads.WeatherUtils.*;

import java.util.concurrent.Callable;

import static chapter.threads.threads.WeatherServer.*;
import chapter.threads.threads.CityTemperature;

public record CallableWeatherTask(String city) implements Callable<CityTemperature> {
    @Override
    public CityTemperature call() {
        long begin = getTime();
        print("Starting "+city);
        try {
            String result = getTemperature(LOCAL_URL, city);
            int temp = extractTemperature(city,result);
            var r = new CityTemperature(city,temp);
            long rtt = getTime()-begin;
            print("("+result+") RTT: "+rtt+"ms");
            return r;
        }
        catch (Throwable e) {}
        throw new RuntimeException(city+":Error.");      
    }
}
