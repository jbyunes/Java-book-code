package chapter.threads.parallel.streams;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import chapter.threads.threads.Utils;


public class TestSpliterator {
    public static void main(String[] args) {
        var l = IntStream.range(2000, 2025)
            .mapToObj(Year::of)
            .flatMap(y -> Arrays.stream(Month.values())
                                .map(y::atMonth))
            .toList();
        var s = l.spliterator();
        var s1 = s.trySplit();
        if (s1 != null) {
            System.out.println("First part:");
            while (s1.tryAdvance(System.out::println));
            System.out.println("Second part:");
            while (s.tryAdvance(System.out::println));
        } else {
            System.out.println("No split");
            s.forEachRemaining(System.out::println);
        }
        
        try (var service = Executors.newFixedThreadPool(2)) {
            var secondPart = l.spliterator();
            var firstPart = secondPart.trySplit();
            service.execute(() ->firstPart.forEachRemaining(Utils::print));
            service.execute(() ->secondPart.forEachRemaining(Utils::print));
        }
    }
}
