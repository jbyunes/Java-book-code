package chapter.threads.parallel.streams;

import static chapter.threads.threads.Utils.*;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class RangeSpliterator implements Spliterator<Integer> {
    private int start, end, current;
    public static RangeSpliterator of(int l) {
        return RangeSpliterator.of(0, l);
    }
    public static RangeSpliterator of(int start, int end) {
        return new RangeSpliterator(start, end);
    }
    private RangeSpliterator(int start, int end) {
        this.start = start;
        this.end = end;
        this.current = start;
    }
    @Override public boolean tryAdvance(Consumer<? super Integer> action) {
        if (current == end) { return false; }
        print("Advance: "+current);
        action.accept(current++);
        return true;
    }
    @Override
    public RangeSpliterator trySplit() {
        if (end - current < 4) { return null; }
        int middle = (current+end)/2;
        var left = new RangeSpliterator(current, middle);
        start = middle;
        current = start;
        return left;
    }
    @Override public long estimateSize() { return end-start; }
    public Comparator<? super Integer> getComparator() {
        return null; // natural order
    }
    @Override public int characteristics() {
        return
                Spliterator.SIZED
                | Spliterator.SUBSIZED
                | Spliterator.NONNULL
                | Spliterator.IMMUTABLE
//                | Spliterator.CONCURRENT
                | Spliterator.ORDERED
                | Spliterator.DISTINCT
                | Spliterator.SORTED              
                ;
    }
}
