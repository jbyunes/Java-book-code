package chapter.threads.parallel.streams;

import chapter.threads.threads.Utils;
import static chapter.threads.threads.Utils.*;

import java.util.stream.StreamSupport;

public class RangeSpliteratorExample {
    public static void main(String []args) {
        var rs = RangeSpliterator.of(0, 8);
        StreamSupport.stream(rs, true)
            .peek(Utils::print)
            .forEachOrdered(e -> print("end:"+e));        
    }
}
