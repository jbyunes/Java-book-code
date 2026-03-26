package chapter.threads.parallel.streams;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;


import java.util.Collections;

import static chapter.threads.parallel.streams.Utils.*;
import static chapter.threads.threads.Utils.*;

public class TestFlush {
    private static void experience(Consumer<List<Double>> flush,
                                   DoubleUnaryOperator f,
                                   String msg) {
        final int START = 200_000;
        final int END = 2_000_000;
        final int STEP = 200_000;
        System.out.println("\n"+msg);
        for (int size = START;size <= END; size += STEP) {
            var l = listOf(size);
            flush.accept(l);
            long timeSequential = measure(() -> l.stream().mapToDouble(x -> x).map(f).reduce(0d, Double::sum));
            long timeParallel = measure(() -> l.parallelStream().mapToDouble(x -> x).map(f).reduce(0d, Double::sum));
            double factor = (double)timeSequential/timeParallel;
            System.out.printf(Locale.ENGLISH,
                "Size: %10d Sequential: %7dµs Parallel: %6dµs Acc. factor: %7.4f\n",
                l.size(),timeSequential, timeParallel, factor);
        }
    }
    private static void experience(Consumer<List<Double>> flush,
            Function<Double,Double> f,
            String msg) {
        final int START = 200_000;
        final int END = 2_000_000;
        final int STEP = 200_000;
        System.out.println("\n"+msg);
        for (int size = START;size <= END; size += STEP) {
            var l = listOf(size);
            flush.accept(l);
            long timeSequential = measure(() -> l.stream().map(f).reduce(0d, Double::sum));
            long timeParallel = measure(() -> l.parallelStream().map(f).reduce(0d, Double::sum));
            double factor = (double)timeSequential/timeParallel;
            System.out.printf(Locale.ENGLISH,
                              "Size: %10d Sequential: %7dµs Parallel: %6dµs Acc. factor: %7.4f\n",
                              l.size(),timeSequential, timeParallel, factor);
        }
    }
    public static void main(String[] args) {
        printInfos();

        Function<Double,Double> f = d -> 2*d;
        experience(l -> {}, f, "d -> d*1.5");
//        experience(l -> {}, d -> log(d), "d -> log(d)");
//        experience(l -> {}, d -> sqrt(100*(cos(1000*sin(log(d))))),
//                   "d -> d -> sqrt(100*(cos(1000*sin(log(d)))))");
        
        experience(Collections::shuffle, f, "flushed, d -> d*1.5");
//        experience(Collections::shuffle, d -> log(d), "flushed, d -> log(d)");
//        experience(Collections::shuffle, d -> sqrt(100*(cos(1000*sin(log(d))))),
//                   "flushed, d -> sqrt(100*(cos(1000*sin(log(d)))))");
    }
}
/*
Mac OS X, 15.5, aarch64
Oracle Corporation, 24.0.1
Available processing units: 14

d -> d*2
Size:     200000 Sequential:     137µs Parallel:     70µs Acc. factor:  1.9571
Size:     400000 Sequential:     254µs Parallel:     73µs Acc. factor:  3.4795
Size:     600000 Sequential:     374µs Parallel:     87µs Acc. factor:  4.2989
Size:     800000 Sequential:     499µs Parallel:    101µs Acc. factor:  4.9406
Size:    1000000 Sequential:     637µs Parallel:    115µs Acc. factor:  5.5391
Size:    1200000 Sequential:     766µs Parallel:    169µs Acc. factor:  4.5325
Size:    1400000 Sequential:     895µs Parallel:    167µs Acc. factor:  5.3593
Size:    1600000 Sequential:    1030µs Parallel:    189µs Acc. factor:  5.4497
Size:    1800000 Sequential:    1157µs Parallel:    209µs Acc. factor:  5.5359
Size:    2000000 Sequential:    1303µs Parallel:    241µs Acc. factor:  5.4066

d -> log(d)
Size:     200000 Sequential:    1176µs Parallel:    169µs Acc. factor:  6.9586
Size:     400000 Sequential:    2392µs Parallel:    296µs Acc. factor:  8.0811
Size:     600000 Sequential:    3535µs Parallel:    405µs Acc. factor:  8.7284
Size:     800000 Sequential:    4772µs Parallel:    555µs Acc. factor:  8.5982
Size:    1000000 Sequential:    5955µs Parallel:    649µs Acc. factor:  9.1757
Size:    1200000 Sequential:    7176µs Parallel:    798µs Acc. factor:  8.9925
Size:    1400000 Sequential:    8448µs Parallel:    901µs Acc. factor:  9.3762
Size:    1600000 Sequential:    9806µs Parallel:   1041µs Acc. factor:  9.4198
Size:    1800000 Sequential:   11056µs Parallel:   1148µs Acc. factor:  9.6307
Size:    2000000 Sequential:   12272µs Parallel:   1258µs Acc. factor:  9.7552

d -> sqrt(100*(cos(1000*sin(log(d)))))
Size:     200000 Sequential:    7257µs Parallel:    762µs Acc. factor:  9.5236
Size:     400000 Sequential:   14505µs Parallel:   1447µs Acc. factor: 10.0242
Size:     600000 Sequential:   21742µs Parallel:   2178µs Acc. factor:  9.9826
Size:     800000 Sequential:   29014µs Parallel:   2886µs Acc. factor: 10.0534
Size:    1000000 Sequential:   36374µs Parallel:   3632µs Acc. factor: 10.0149
Size:    1200000 Sequential:   43605µs Parallel:   4347µs Acc. factor: 10.0311
Size:    1400000 Sequential:   50791µs Parallel:   5051µs Acc. factor: 10.0556
Size:    1600000 Sequential:   58066µs Parallel:   5770µs Acc. factor: 10.0634
Size:    1800000 Sequential:   65312µs Parallel:   6597µs Acc. factor:  9.9003
Size:    2000000 Sequential:   72579µs Parallel:   7184µs Acc. factor: 10.1029

flushed, d -> d*2
Size:     200000 Sequential:     820µs Parallel:    141µs Acc. factor:  5.8156
Size:     400000 Sequential:    1710µs Parallel:    217µs Acc. factor:  7.8802
Size:     600000 Sequential:    3087µs Parallel:    441µs Acc. factor:  7.0000
Size:     800000 Sequential:    5121µs Parallel:    726µs Acc. factor:  7.0537
Size:    1000000 Sequential:    7022µs Parallel:    964µs Acc. factor:  7.2842
Size:    1200000 Sequential:   11174µs Parallel:    674µs Acc. factor: 16.5786
Size:    1400000 Sequential:   17318µs Parallel:   1444µs Acc. factor: 11.9931
Size:    1600000 Sequential:   22180µs Parallel:   1915µs Acc. factor: 11.5822
Size:    1800000 Sequential:   26886µs Parallel:   2418µs Acc. factor: 11.1191
Size:    2000000 Sequential:   31287µs Parallel:   2874µs Acc. factor: 10.8862

flushed, d -> log(d)
Size:     200000 Sequential:    2438µs Parallel:    290µs Acc. factor:  8.4069
Size:     400000 Sequential:    5036µs Parallel:    590µs Acc. factor:  8.5356
Size:     600000 Sequential:    8151µs Parallel:    957µs Acc. factor:  8.5172
Size:     800000 Sequential:   11975µs Parallel:   1526µs Acc. factor:  7.8473
Size:    1000000 Sequential:   17156µs Parallel:   2301µs Acc. factor:  7.4559
Size:    1200000 Sequential:   24204µs Parallel:   1886µs Acc. factor: 12.8335
Size:    1400000 Sequential:   30193µs Parallel:   3577µs Acc. factor:  8.4409
Size:    1600000 Sequential:   38389µs Parallel:   3642µs Acc. factor: 10.5406
Size:    1800000 Sequential:   46419µs Parallel:   4374µs Acc. factor: 10.6125
Size:    2000000 Sequential:   54680µs Parallel:   5151µs Acc. factor: 10.6154

flushed, d -> sqrt(100*(cos(1000*sin(log(d)))))
Size:     200000 Sequential:    7671µs Parallel:    795µs Acc. factor:  9.6491
Size:     400000 Sequential:   15269µs Parallel:   1568µs Acc. factor:  9.7379
Size:     600000 Sequential:   23248µs Parallel:   2441µs Acc. factor:  9.5240
Size:     800000 Sequential:   31836µs Parallel:   3288µs Acc. factor:  9.6825
Size:    1000000 Sequential:   40730µs Parallel:   4641µs Acc. factor:  8.7761
Size:    1200000 Sequential:   54279µs Parallel:   5833µs Acc. factor:  9.3055
Size:    1400000 Sequential:   66765µs Parallel:   7027µs Acc. factor:  9.5012
Size:    1600000 Sequential:   80028µs Parallel:   7997µs Acc. factor: 10.0073
Size:    1800000 Sequential:   93128µs Parallel:   8600µs Acc. factor: 10.8288
Size:    2000000 Sequential:  107335µs Parallel:  10513µs Acc. factor: 10.2097
*/
/*
Mac OS X, 15.6.1, aarch64
Oracle Corporation, 25
Available processing units: 14
Thread pool size: 13

d -> d*2
Size:     200000 Sequential:     147µs Parallel:     80µs Acc. factor:  1.8375
Size:     400000 Sequential:     329µs Parallel:     76µs Acc. factor:  4.3289
Size:     600000 Sequential:     489µs Parallel:    123µs Acc. factor:  3.9756
Size:     800000 Sequential:     774µs Parallel:    205µs Acc. factor:  3.7756
Size:    1000000 Sequential:    1253µs Parallel:    349µs Acc. factor:  3.5903
Size:    1200000 Sequential:    2022µs Parallel:    260µs Acc. factor:  7.7769
Size:    1400000 Sequential:    2666µs Parallel:    615µs Acc. factor:  4.3350
Size:    1600000 Sequential:    3485µs Parallel:    730µs Acc. factor:  4.7740
Size:    1800000 Sequential:    4388µs Parallel:    851µs Acc. factor:  5.1563
Size:    2000000 Sequential:    5463µs Parallel:    973µs Acc. factor:  5.6146

d -> log(d)
Size:     200000 Sequential:    1484µs Parallel:    198µs Acc. factor:  7.4949
Size:     400000 Sequential:    3053µs Parallel:    392µs Acc. factor:  7.7883
Size:     600000 Sequential:    5159µs Parallel:    662µs Acc. factor:  7.7931
Size:     800000 Sequential:    7643µs Parallel:   1061µs Acc. factor:  7.2036
Size:    1000000 Sequential:   10180µs Parallel:   1214µs Acc. factor:  8.3855
Size:    1200000 Sequential:   14592µs Parallel:   1520µs Acc. factor:  9.6000
Size:    1400000 Sequential:   19430µs Parallel:   1876µs Acc. factor: 10.3571
Size:    1600000 Sequential:   24649µs Parallel:   2233µs Acc. factor: 11.0385
Size:    1800000 Sequential:   29413µs Parallel:   2344µs Acc. factor: 12.5482
Size:    2000000 Sequential:   33985µs Parallel:   3145µs Acc. factor: 10.8060

d -> d -> sqrt(100*(cos(1000*sin(log(d)))))
Size:     200000 Sequential:    7136µs Parallel:    722µs Acc. factor:  9.8837
Size:     400000 Sequential:   14359µs Parallel:   1445µs Acc. factor:  9.9370
Size:     600000 Sequential:   21852µs Parallel:   2243µs Acc. factor:  9.7423
Size:     800000 Sequential:   29641µs Parallel:   3230µs Acc. factor:  9.1768
Size:    1000000 Sequential:   39316µs Parallel:   4363µs Acc. factor:  9.0112
Size:    1200000 Sequential:   49612µs Parallel:   4898µs Acc. factor: 10.1290
Size:    1400000 Sequential:   55313µs Parallel:   5411µs Acc. factor: 10.2223
Size:    1600000 Sequential:   73133µs Parallel:   7248µs Acc. factor: 10.0901
Size:    1800000 Sequential:   85390µs Parallel:   7878µs Acc. factor: 10.8390
Size:    2000000 Sequential:   97210µs Parallel:   9729µs Acc. factor:  9.9918

flushed, d -> d*2
Size:     200000 Sequential:     668µs Parallel:    122µs Acc. factor:  5.4754
Size:     400000 Sequential:    1394µs Parallel:    206µs Acc. factor:  6.7670
Size:     600000 Sequential:    2610µs Parallel:    404µs Acc. factor:  6.4604
Size:     800000 Sequential:    4171µs Parallel:    459µs Acc. factor:  9.0871
Size:    1000000 Sequential:    5969µs Parallel:    830µs Acc. factor:  7.1916
Size:    1200000 Sequential:    9657µs Parallel:   1030µs Acc. factor:  9.3757
Size:    1400000 Sequential:   13696µs Parallel:   1242µs Acc. factor: 11.0274
Size:    1600000 Sequential:   18176µs Parallel:   1570µs Acc. factor: 11.5771
Size:    1800000 Sequential:   21704µs Parallel:   1939µs Acc. factor: 11.1934
Size:    2000000 Sequential:   24980µs Parallel:   1889µs Acc. factor: 13.2239

flushed, d -> log(d)
Size:     200000 Sequential:    2185µs Parallel:    251µs Acc. factor:  8.7052
Size:     400000 Sequential:    4604µs Parallel:    403µs Acc. factor: 11.4243
Size:     600000 Sequential:    7354µs Parallel:    848µs Acc. factor:  8.6722
Size:     800000 Sequential:   10970µs Parallel:   1460µs Acc. factor:  7.5137
Size:    1000000 Sequential:   14957µs Parallel:   2031µs Acc. factor:  7.3644
Size:    1200000 Sequential:   20997µs Parallel:   2440µs Acc. factor:  8.6053
Size:    1400000 Sequential:   28211µs Parallel:   2650µs Acc. factor: 10.6457
Size:    1600000 Sequential:   36082µs Parallel:   3105µs Acc. factor: 11.6206
Size:    1800000 Sequential:   43475µs Parallel:   4101µs Acc. factor: 10.6011
Size:    2000000 Sequential:   50272µs Parallel:   4279µs Acc. factor: 11.7485

flushed, d -> sqrt(100*(cos(1000*sin(log(d)))))
Size:     200000 Sequential:    7304µs Parallel:    733µs Acc. factor:  9.9645
Size:     400000 Sequential:   14707µs Parallel:   1482µs Acc. factor:  9.9238
Size:     600000 Sequential:   22345µs Parallel:   2318µs Acc. factor:  9.6398
Size:     800000 Sequential:   30361µs Parallel:   3158µs Acc. factor:  9.6140
Size:    1000000 Sequential:   41507µs Parallel:   4331µs Acc. factor:  9.5837
Size:    1200000 Sequential:   50852µs Parallel:   5387µs Acc. factor:  9.4398
Size:    1400000 Sequential:   62849µs Parallel:   6615µs Acc. factor:  9.5010
Size:    1600000 Sequential:   74246µs Parallel:   7743µs Acc. factor:  9.5888
Size:    1800000 Sequential:   86816µs Parallel:   8753µs Acc. factor:  9.9184
Size:    2000000 Sequential:   99057µs Parallel:   9936µs Acc. factor:  9.9695
*/