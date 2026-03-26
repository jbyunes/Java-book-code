package chapter.threads.threads;

import static chapter.threads.threads.Utils.*;

import java.net.URI;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;

public class WeatherUtils {
    private static Pattern p = Pattern.compile("(\\d+)");
    public static String getTemperature(String server, String city) {
        String result = "";
        try {
            String uri = server+"/"+city +"?format=%25l:%25t";
            var url = new URI(uri).toURL();
            try (var sc = new Scanner(url.openStream())) {
                if (sc.hasNextLine()) {
                    result = sc.nextLine();
                } else {
                    throw new RuntimeException("can't read");
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(city+":"+e.getMessage());
        }
        return result;
    }
    public static Integer extractTemperature(String city, String answer) {
        try {
            var m = p.matcher(answer);
            if (m.find()) {
                int temp = Integer.parseInt(m.group());
                return temp;
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(city+":"+e.getMessage());
        }
        return 0;
    }
    public static void timeRun(String server, String city,
            BinaryOperator<String> job) {
        long begin = getTime();
        print("Starting: "+city);
        String result;
        try {
            result = job.apply(server, city);
        } catch(Throwable e) {
            result = e.getMessage();
        }
        long rtt = getTime()-begin;
        print("("+result+") RTT: "+rtt+"ms");
    }

}
