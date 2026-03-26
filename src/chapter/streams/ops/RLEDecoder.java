package chapter.streams.ops;

import java.util.stream.Gatherer.Downstream;

public class RLEDecoder {
    private Integer last = null;
    public boolean gather(Integer e, Downstream<? super Integer> d) {
        if (last==null) {
            last = e;
            return true;
        }
        for (int i=0; i<last; i++) { d.push(e); }
        last = null;
        return true;
    }
}

