package chapter.streams.ops;

import java.util.stream.IntStream;

public class TestLimit {
    public static void main(String[] args) {
        IntStream.iterate(0, i -> i + 1)
            .limit(10)
            .filter(i -> i % 2 == 0)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
        
        IntStream.iterate(0, i -> i+1)
            .filter(i -> i%2==0)
            .limit(10)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
    }
}
