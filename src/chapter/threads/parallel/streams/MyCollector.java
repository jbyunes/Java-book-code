package chapter.threads.parallel.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;

public class MyCollector implements Collector<Integer,List<Integer>,List<Integer>> {
    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList::new;
    }
    @Override
    public BiConsumer<List<Integer>, Integer> accumulator() {
        return (l,e) -> {
            synchronized (l) {
                System.out.println("Accumulate: "+l+" "+e);
                l.add(e);
            }
        };
    }
    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (l1, l2) -> {
            System.out.println("Combine: "+l1+" "+l2);
            var r = new ArrayList<Integer>();
            r.addAll(l1);
            r.addAll(l2);
            return r;
        };
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(CONCURRENT, UNORDERED, IDENTITY_FINISH);
    }
    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return null;
    }
}
