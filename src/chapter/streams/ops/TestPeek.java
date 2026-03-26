package chapter.streams.ops;

import java.util.stream.IntStream;

public class TestPeek {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 3)
            .peek(i -> System.out.println("Before dropWhile: " + i))
            .dropWhile(i -> i < 10)
            .peek(i -> System.out.println("After dropWhile: " + i))
            .limit(5)
            .forEach(i -> System.out.println("Result " + i));
        System.out.println();
    }
}
