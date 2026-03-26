package chapter.streams.ops;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestForeach {
    public static void main(String[] args) {
        Stream<LocalDate> februaryDays = Stream.iterate(
            Year.now().atMonth(Month.FEBRUARY).atDay(1),
            d -> d.getMonth()==Month.FEBRUARY,
            d -> d.plusDays(1));
        februaryDays.forEach(System.out::println);

        var fibonacciSupplier = new IntSupplier() {
            private int n1=1, n2=1, n=1;
            public int getAsInt() {
                if (n<3) { n++; return 1; }
                int v = n1+n2; n2 = n1; n1 = v; n++; return n1;
            }
        };
        IntStream.generate(fibonacciSupplier)
            .limit(100)
            .forEach(System.out::println);
        
        
        IntStream.iterate(0, i -> i<10, i -> i+1)
            .filter(i -> i % 2 == 0)
            .forEach(i -> System.out.print(i+" "));
        System.out.println();
                
        "toto".chars().forEach(c -> System.out.print((char)c));
        "toto".codePoints().forEach(c -> System.out.print(c));

    }
}
