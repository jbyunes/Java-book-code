package chapter.threads.parallel;

import java.math.BigInteger;
import static java.math.BigInteger.*;
import static chapter.threads.parallel.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

public class RecursiveSummer extends RecursiveTask<BigInteger> {
    private static final long serialVersionUID = 1L;

    public record Interval(BigInteger start, BigInteger end) {
        public BigInteger middle() {
            return start.add(end).divide(TWO);
        }

        public BigInteger length() {
            return end.subtract(start);
        }
    }
    private Interval interval;
    private final BigInteger THRESHOLD = BigInteger.valueOf(1_000_000);
    
    private RecursiveSummer(BigInteger start, BigInteger end) {
        interval = new Interval(start, end);
    }

    public RecursiveSummer(BigInteger end) {
        this(ZERO, end);
        threads = new ConcurrentHashMap<>();
    }
    
    public static ConcurrentHashMap<Long, List<Interval>> threads;

    @Override
    protected BigInteger compute() {
        // gather stats
        var l = new ArrayList<Interval>();
        l.add(interval);
        threads.merge(tid(), l,
            (old, value) -> {
            old.addAll(value);
            return old;
        });
        // base cases
        if (interval.length().compareTo(THRESHOLD) <= 0) {
            var sum = ZERO;
            for (var i = interval.start;
                i.compareTo(interval.end) < 0;
                i = i.add(ONE)) {
                sum = sum.add(i);
            }
            return sum;
        }
        // recursive splits
        var middle = interval.middle();
        var left = new RecursiveSummer(interval.start, middle).fork();
        interval = new Interval(middle, interval.end);
        var rValue = compute();
        var lValue = left.join();
        return lValue.add(rValue);
    }
}
