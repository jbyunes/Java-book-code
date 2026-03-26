package chapter.streams.ops;

import java.util.stream.Gatherer.Downstream;

public class RLEEncoder {
    private Integer last = null;
    private int runlength = 0;
    public boolean gather(Integer e, Downstream<? super Integer> d) {
        if (last==null) {
            last = e;
            runlength = 1;
            return true;
        }
        if (e==last) {
            runlength++;
            return true;
        }
        d.push(runlength);
        d.push(last);
        last = e;
        runlength = 1;
        return true;
    }
    public void finish(Downstream<? super Integer> d) {
        if (last!=null) {
            d.push(runlength);
            d.push(last);
        }
    }
}

