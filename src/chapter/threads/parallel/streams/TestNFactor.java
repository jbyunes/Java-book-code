package chapter.threads.parallel.streams;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.parallel.streams.Utils.*;

import java.util.TreeMap;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.DoubleStream;

public class TestNFactor {
    public static final int START = 10_000;
    public static int WARMUP = 100_000;
    public static int END = 100_000_000;
    public static int STEP = 10_000;
    private static TreeMap<Long, Result>
    experience(int start, int end, DoubleUnaryOperator f, String msg) {
        System.out.println("\n"+msg);
        var result = new TreeMap<Long, Result>();
        int step = STEP;
        for (int size=start; size <= end; size += step, step *=2) {
            System.out.print((size/1_000)+"/"+(end/1_000)+" ");
            var source = Utils.createArray(size);
            long seq = measure(() -> DoubleStream.of(source)
                .map(f)
                .reduce(0d, Double::sum));
            long par = measure(() -> DoubleStream.of(source)
                .parallel()
                .map(f)
                .reduce(0d, Double::sum));
            double factor = (double)seq/par;
            result.put((long)size, new Result(size, 1, seq, par, factor));
        }
        System.out.println("-");
        return result;
    }
    public static void main(String[] args) {
        printInfos();

        var rw = experience(START, WARMUP, d -> d*1.5d, "d -> d*1.5");
        print(rw);
        printGnuplot("d -> d*1.5", rw, Result::dataLength);
        
        var rd = experience(START, END, d -> d*1.5d, "d -> d*1.5");
        print(rd);
        printGnuplot("d -> d*1.5", rd, Result::dataLength);
    }
}
/*
Mac OS X, 26.0.1, aarch64
Oracle Corporation, 25
Available processing units: 14
Thread pool size: 13

From List: d -> d*1.5
10/100000 20/100000 40/100000 80/100000 160/100000 320/100000 640/100000 1280/100000 2560/100000 5120/100000 10240/100000 20480/100000 40960/100000 81920/100000 -
     10000 Size:      10000 Seq:      76µs Par:    124µs Factor:  0.6129
     20000 Size:      20000 Seq:      56µs Par:     91µs Factor:  0.6154
     40000 Size:      40000 Seq:     122µs Par:     60µs Factor:  2.0333
     80000 Size:      80000 Seq:     248µs Par:     69µs Factor:  3.5942
    160000 Size:     160000 Seq:     484µs Par:    113µs Factor:  4.2832
    320000 Size:     320000 Seq:    1005µs Par:    178µs Factor:  5.6461
    640000 Size:     640000 Seq:    2020µs Par:    287µs Factor:  7.0383
   1280000 Size:    1280000 Seq:    4043µs Par:    545µs Factor:  7.4183
   2560000 Size:    2560000 Seq:    8121µs Par:   1025µs Factor:  7.9229
   5120000 Size:    5120000 Seq:   16292µs Par:   2145µs Factor:  7.5953
  10240000 Size:   10240000 Seq:   32997µs Par:   4040µs Factor:  8.1676
  20480000 Size:   20480000 Seq:   65905µs Par:   8112µs Factor:  8.1244
  40960000 Size:   40960000 Seq:  132555µs Par:  16754µs Factor:  7.9118
  81920000 Size:   81920000 Seq:  266669µs Par:  32287µs Factor:  8.2593

# d -> d*1.5
10000  0.6129
20000  0.6154
40000  2.0333
80000  3.5942
160000  4.2832
320000  5.6461
640000  7.0383
1280000  7.4183
2560000  7.9229
5120000  7.5953
10240000  8.1676
20480000  8.1244
40960000  7.9118
81920000  8.2593

 
 
*/