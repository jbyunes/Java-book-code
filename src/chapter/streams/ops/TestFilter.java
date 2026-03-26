package chapter.streams.ops;

import java.util.stream.IntStream;

public class TestFilter {
    public static void main(String[] args) {
        IntStream.range(0,10)
            .filter(i -> i % 2 == 0)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
        
        IntStream.range(1890,2024)
            .filter(y -> y%4==0)
            .filter(y -> !(y%100==0) || y%400==0)
            .forEach(y -> System.out.print(y+" "));
        System.out.println();

    }
}
