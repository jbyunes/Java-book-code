package chapter.threads.parallel;

import static chapter.threads.parallel.Utils.tid;
import static java.math.BigInteger.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class ProductTask extends RecursiveTask<BigInteger> {
    private static final long serialVersionUID = 1L;

    public record Interval(BigInteger start, BigInteger end) {
        public BigInteger length() {
            return end.subtract(start);
        }
        public BigInteger middle() {
            return start.add(end).divide(TWO);
        }
    }
    private Interval interval;
    private final BigInteger THRESHOLD = BigInteger.valueOf(10_000);
    
    private ProductTask(BigInteger start, BigInteger end) {
        interval = new Interval(start, end);
    }

    public ProductTask(BigInteger end) {
        this(ONE, end);
        threads = new ConcurrentHashMap<>();
    }
    
    public static ConcurrentHashMap<Long, List<Interval>> threads;

    @Override
    protected BigInteger compute() {
        var l = new ArrayList<Interval>();
        l.add(interval);
        threads.merge(tid(), l,
            (old, value) -> {
            old.addAll(value);
            return old;
        });
        if (interval.length().compareTo(THRESHOLD) <= 0) {
            var product = ONE;
            for (var i = interval.start;
                i.compareTo(interval.end) < 0;
                i = i.add(ONE)) {
                product = product.multiply(i);
            }
            return product;
        }
        var middle = interval.middle();
        var left = new ProductTask(interval.start, middle).fork();
        interval = new Interval(middle, interval.end);
        var rValue = compute();
        return left.join().multiply(rValue);
    }


}
