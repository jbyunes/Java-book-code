package chapter.threads.parallel.streams;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import static java.lang.Math.*;

public class Utils {
    public static final Function<Stream<Double>, DoubleStream>
        TO_DOUBLE = s -> s.mapToDouble(x -> x);
    public static void print(TreeMap<Long,Result> result) {
        result.forEach( (i,r) ->
        System.out.printf(Locale.ENGLISH,
            "%10d Size: %10d Seq: %7dµs Par: %6dµs Factor: %7.4f\n",
            i, r.dataLength(), r.sequential(),
            r.parallel(), r.acceleration()));

    }
    public static void printGnuplot(String message,
            TreeMap<Long,Result> result,
            Function<Result,Long> x) {
        System.out.println("\n# "+message);
        result.forEach( (i,r) ->
            System.out.printf(Locale.ENGLISH,"%d %7.4f\n",
                              x.apply(r), r.acceleration()));
            System.out.println();
    }
    public static <T> long measure(Callable<T> callable) {
        final int REPEAT = 100;
        long start = System.nanoTime();
        try { for (int i=0; i<REPEAT; i++) { callable.call(); } }
        catch (Exception e) { return Long.MAX_VALUE; }
        long end = System.nanoTime();
        return (end - start) / (REPEAT*1_000);
    }
    public static List<Double> createList(long size,
            Supplier<List<Double>> s) {
        return new Random(1).doubles(size)
            .map(Math::abs)
            .map(x -> x+1)
            .mapToObj(x -> x)
            .collect(toCollection(s));  
    }
    public static double[] createArray(int size) {
        var r = new Random(1);
        double[] a = new double[size];
        for (int i=0; i<size; i++) {
            a[i] = abs(r.nextDouble())+1;
        }
        return a;
    }
    private Utils() {}
    public static List<Double> listOf(int size) {
        Random random = new Random(1);
        List<Double> items = random.doubles(size)
                .mapToObj(d -> Double.valueOf(abs(d)+1))
                .collect(toCollection(ArrayList::new));
        Collections.shuffle(items);
        return items;
    }
}
