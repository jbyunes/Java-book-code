package chapter.streams.ops;

import java.util.List;
import java.util.stream.IntStream;

public class TestCount {
    public static void main(String[] args) {
        long n = IntStream.of(1, 10, 3).count();
        System.out.println("There are " + n + " elements.");
        
        n = List.of(1, 10, 3).stream()
            .peek(System.out::println)
            .count();
        System.out.println("There are " + n + " elements.");
        
        n = IntStream.iterate(0, i->i<4, i->i+1)
            .peek(System.out::println)
            .count();
        System.out.println("There are " + n + " elements.");
    }
}
