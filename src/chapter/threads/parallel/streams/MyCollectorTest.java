package chapter.threads.parallel.streams;

import java.util.stream.StreamSupport;

public class MyCollectorTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            for (; i<100; i++) {
                var r = RangeSpliterator.of(50);
                var s = StreamSupport.stream(r, true);
                var c = s.collect(new MyCollector());
                System.out.println(c);
            }
        } catch(Throwable t) {
            t.printStackTrace();
            System.out.println(i);
        }
    }

}
