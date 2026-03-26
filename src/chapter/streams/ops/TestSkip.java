package chapter.streams.ops;

import java.util.stream.IntStream;

public class TestSkip {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 1)
            .skip(100)
            .filter(i -> i % 2 == 0)
            .skip(100)
            .limit(10)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
    }
}
