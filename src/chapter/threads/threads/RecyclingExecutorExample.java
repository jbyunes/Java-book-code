package chapter.threads.threads;

import static chapter.threads.threads.Utils.*;

public class RecyclingExecutorExample {
    public static void main(String[] args) throws Exception {
        var s = new RecyclingExecutor();              
        print("Main");
        cities.forEach(c -> s.execute(new WeatherTask(c)));
        print("Main is free to continue");
        s.stop();
        print("Main requested end of service");
    }
}
