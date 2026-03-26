package chapter.threads.threads;

import static chapter.threads.threads.WeatherUtils.*;
import static chapter.threads.threads.WeatherServer.*;

public class WeatherTask implements Runnable {
    protected String city;
    public WeatherTask(String city) { this.city = city; }    
    @Override public void run() {
        timeRun(LOCAL_URL, city, WeatherUtils::getTemperature);
    }   
}