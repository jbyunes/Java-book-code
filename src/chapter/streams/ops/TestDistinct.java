package chapter.streams.ops;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestDistinct {
    public static void main(String[] args) {
        IntStream.of(0, 1, 2, 3, 4, 3, 2, 1, 5, 8, 6, 7, 5, 5, 5, 11)
        .distinct()
        .forEach(i -> System.out.print(i+" "));
        System.out.println();

        Stream.of(new Pair(1, 1), new Pair(2, 6), new Pair(1, 2), new Pair(2, 1), new Pair(1, 7))
        .distinct()
        .forEach(i -> System.out.print(i+" "));
        System.out.println();

        long c = IntStream.iterate(0, i -> i + 1)
                .limit(1_000)
                .distinct()
                .count();
        System.out.println(c);

        c = IntStream.generate(() -> 666)
                .limit(1000)
                .distinct()
                .count();
        System.out.println(c);

    }
}
