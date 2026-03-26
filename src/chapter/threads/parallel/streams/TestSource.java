package chapter.threads.parallel.streams;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.parallel.streams.Utils.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.LongStream;

public class TestSource {
    private static final int START = 10_000;
    private static final int END = 100_000_000;
    private static final int STEP = 10_000;
    private static TreeMap<Long, Result>
    experience(DoubleUnaryOperator f,
            String msg,
            Supplier<List<Double>> listSupplier) {
        int step = STEP;
        System.out.println("\n"+msg);
        var result = new TreeMap<Long, Result>();
        for (long size=START; size <= END; size += step, step *=2) {
            System.out.print((size/1_000)+"/"+(END/1_000)+" ");
            List<Double> source = createList(size, listSupplier);
            long seq = measure(() -> TO_DOUBLE.apply(source.stream())
                                              .map(f)
                                              .reduce(0d, Double::sum));
            long par = measure(() -> TO_DOUBLE.apply(source.stream())
                                              .parallel()
                                              .map(f)
                                              .reduce(0d, Double::sum));
            double factor = (double)seq/par;
            result.put(size, new Result(size, 1, seq, par, factor));
        }
        System.out.println("-");
        return result;
    }
    private static TreeMap<Long, Result>
    experience2(DoubleUnaryOperator f, String msg) {
        int step = STEP;
        System.out.println("\n"+msg);
        var result = new TreeMap<Long, Result>();
        for (long size=START; size <= END; size += step, step *=2) {
            long final_size = size;
            System.out.print((size/1_000)+"/"+(END/1_000)+" ");
            long seq = measure(() -> LongStream.range(1, final_size)
                                              .mapToDouble(x -> x)
                                              .map(f)
                                              .reduce(0d, Double::sum));
            long par = measure(() -> LongStream.range(1, final_size)
                                              .mapToDouble(x -> x)
                                              .parallel()
                                              .map(f)
                                              .reduce(0d, Double::sum));
            double factor = (double)seq/par;
            result.put(size, new Result(size, 1, seq, par, factor));
        }
        System.out.println("-");
        return result;
    }
    public static void main(String[] args) {
        printInfos();

        var ral = experience(d -> d*1.5d,
                             "From ArrayList: d -> d*1.5", ArrayList::new);
        print(ral);
        printGnuplot("From ArrayList: d -> d*1.5", ral, Result::dataLength);

        var rll = experience(d -> d*1.5d,
                             "From LinkedList: d -> d*1.5", LinkedList::new);
        print(rll);
        printGnuplot("From LinkedList: d -> d*1.5", rll, Result::dataLength);

        var rl = experience2(d -> d*1.5d,
                             "From Long.range: d -> d*1.5");
        print(rl);
        printGnuplot("From Long.range: d -> d*1.5", rl, Result::dataLength);
    }
}
/*
Mac OS X, 26.0.1, aarch64
Oracle Corporation, 25
Available processing units: 14
Thread pool size: 13

From ArrayList: d -> d*1.5
10/100000 20/100000 40/100000 80/100000 160/100000 320/100000 640/100000 1280/100000 2560/100000 5120/100000 10240/100000 20480/100000 40960/100000 81920/100000 -
     10000 Size:      10000 Seq:      72µs Par:    155µs Factor:  0.4645
     20000 Size:      20000 Seq:      57µs Par:     88µs Factor:  0.6477
     40000 Size:      40000 Seq:     122µs Par:     59µs Factor:  2.0678
     80000 Size:      80000 Seq:     247µs Par:     68µs Factor:  3.6324
    160000 Size:     160000 Seq:     486µs Par:    117µs Factor:  4.1538
    320000 Size:     320000 Seq:    1002µs Par:    177µs Factor:  5.6610
    640000 Size:     640000 Seq:    2047µs Par:    288µs Factor:  7.1076
   1280000 Size:    1280000 Seq:    4051µs Par:    530µs Factor:  7.6434
   2560000 Size:    2560000 Seq:    8070µs Par:   1031µs Factor:  7.8274
   5120000 Size:    5120000 Seq:   16442µs Par:   2050µs Factor:  8.0205
  10240000 Size:   10240000 Seq:   33086µs Par:   4073µs Factor:  8.1233
  20480000 Size:   20480000 Seq:   66405µs Par:   8199µs Factor:  8.0992
  40960000 Size:   40960000 Seq:  134031µs Par:  16029µs Factor:  8.3618
  81920000 Size:   81920000 Seq:  268351µs Par:  32168µs Factor:  8.3422

# From ArrayList: d -> d*1.5
10000  0.4645
20000  0.6477
40000  2.0678
80000  3.6324
160000  4.1538
320000  5.6610
640000  7.1076
1280000  7.6434
2560000  7.8274
5120000  8.0205
10240000  8.1233
20480000  8.0992
40960000  8.3618
81920000  8.3422


From LinkedList: d -> d*1.5
10/100000 20/100000 40/100000 80/100000 160/100000 320/100000 640/100000 1280/100000 2560/100000 5120/100000 10240/100000 20480/100000 40960/100000 81920/100000 -
     10000 Size:      10000 Seq:      69µs Par:    119µs Factor:  0.5798
     20000 Size:      20000 Seq:      64µs Par:     80µs Factor:  0.8000
     40000 Size:      40000 Seq:     109µs Par:    116µs Factor:  0.9397
     80000 Size:      80000 Seq:     207µs Par:    638µs Factor:  0.3245
    160000 Size:     160000 Seq:     415µs Par:    309µs Factor:  1.3430
    320000 Size:     320000 Seq:     832µs Par:    944µs Factor:  0.8814
    640000 Size:     640000 Seq:    1732µs Par:   1574µs Factor:  1.1004
   1280000 Size:    1280000 Seq:    3685µs Par:   3013µs Factor:  1.2230
   2560000 Size:    2560000 Seq:    6829µs Par:   7093µs Factor:  0.9628
   5120000 Size:    5120000 Seq:   13785µs Par:  12421µs Factor:  1.1098
  10240000 Size:   10240000 Seq:   27233µs Par:  29576µs Factor:  0.9208
  20480000 Size:   20480000 Seq:   57267µs Par:  46985µs Factor:  1.2188
  40960000 Size:   40960000 Seq:  113048µs Par:  88803µs Factor:  1.2730
  81920000 Size:   81920000 Seq:  233274µs Par: 174489µs Factor:  1.3369

# From LinkedList: d -> d*1.5
10000  0.5798
20000  0.8000
40000  0.9397
80000  0.3245
160000  1.3430
320000  0.8814
640000  1.1004
1280000  1.2230
2560000  0.9628
5120000  1.1098
10240000  0.9208
20480000  1.2188
40960000  1.2730
81920000  1.3369


From Long.range: d -> d*1.5
10/100000 20/100000 40/100000 80/100000 160/100000 320/100000 640/100000 1280/100000 2560/100000 5120/100000 10240/100000 20480/100000 40960/100000 81920/100000 -
     10000 Size:      10000 Seq:     113µs Par:    124µs Factor:  0.9113
     20000 Size:      20000 Seq:      90µs Par:     55µs Factor:  1.6364
     40000 Size:      40000 Seq:     139µs Par:     64µs Factor:  2.1719
     80000 Size:      80000 Seq:     289µs Par:     81µs Factor:  3.5679
    160000 Size:     160000 Seq:     571µs Par:    109µs Factor:  5.2385
    320000 Size:     320000 Seq:    1182µs Par:    170µs Factor:  6.9529
    640000 Size:     640000 Seq:    2122µs Par:    302µs Factor:  7.0265
   1280000 Size:    1280000 Seq:    4331µs Par:    539µs Factor:  8.0353
   2560000 Size:    2560000 Seq:    8663µs Par:   1038µs Factor:  8.3459
   5120000 Size:    5120000 Seq:   16925µs Par:   2062µs Factor:  8.2081
  10240000 Size:   10240000 Seq:   35262µs Par:   4131µs Factor:  8.5359
  20480000 Size:   20480000 Seq:   67850µs Par:   7818µs Factor:  8.6787
  40960000 Size:   40960000 Seq:  135718µs Par:  15498µs Factor:  8.7571
  81920000 Size:   81920000 Seq:  270873µs Par:  31044µs Factor:  8.7255

# From Long.range: d -> d*1.5
10000  0.9113
20000  1.6364
40000  2.1719
80000  3.5679
160000  5.2385
320000  6.9529
640000  7.0265
1280000  8.0353
2560000  8.3459
5120000  8.2081
10240000  8.5359
20480000  8.6787
40960000  8.7571
81920000  8.7255
 */
