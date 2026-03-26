package chapter.streams.ops;

import java.util.stream.Gatherer.Downstream;

public class DistinctAdjacent<T> {
    private T last = null;
    public boolean gather(T e, Downstream<? super T> d) {
        if (last==null || !last.equals(e)) {
            d.push(e);
            last = e;
        }
        return true; // can gather more elements
    }
}
