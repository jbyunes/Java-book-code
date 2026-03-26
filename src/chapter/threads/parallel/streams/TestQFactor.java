package chapter.threads.parallel.streams;

import static chapter.threads.threads.Utils.*;
import static chapter.threads.parallel.streams.Utils.*;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.DoubleStream;

public class TestQFactor {
    public static final Function<DoubleStream, DoubleStream>
        ADD_MAP = s -> s.map(d -> d*1.5d);
    public static final int WARMUP = 5;
    public static final int N = 50;
    private static TreeMap<Long,Result>
    experience(int n, int size, String msg, double[] source) {
        System.out.println("\nSize: "+size+" "+msg);
        Function<DoubleStream, DoubleStream> maps = Function.identity();
        var result = new TreeMap<Long,Result>();
        for (long i=0; i<n; i++) {
            System.out.print(i+"/"+(n-1)+" ");
            final var addMaps = maps;
            long seq = measure(
                () -> addMaps.apply(DoubleStream.of(source))
                      .reduce(0d, Double::sum)
            );
            long par = measure(
                () -> addMaps.apply(DoubleStream.of(source))
                      .parallel()
                      .reduce(0d, Double::sum)
            );
            double factor = (double)seq/par;
            result.put(i, new Result(size, i, seq, par, factor));
            maps = maps.andThen(ADD_MAP); // one more map
        }
        System.out.println('-');
        return result;
    }
    public static void main(String[] args) {
        printInfos();
        
        for (var size : List.of(200_000, 400_000)) {       
            var source = createArray(size);
            var rWarmup = experience(WARMUP, size, "Warmup", source); // warmup
            print(rWarmup);
            printGnuplot(""+size, rWarmup, Result::pipeLength);
          
            var rShort = experience(N, size, "d -> d*1.5", source);
            print(rShort);
            printGnuplot(""+size, rShort, Result::pipeLength);
        }
    }
}
/*
Mac OS X, 26.0.1, aarch64
Oracle Corporation, 25
Available processing units: 14
Thread pool size: 13

Size: 200000 Warmup
0/4 1/4 2/4 3/4 4/4 -
         0 Size:     200000 Seq:     160µs Par:    153µs Factor:  1.0458
         1 Size:     200000 Seq:     203µs Par:     65µs Factor:  3.1231
         2 Size:     200000 Seq:     406µs Par:     95µs Factor:  4.2737
         3 Size:     200000 Seq:     557µs Par:    121µs Factor:  4.6033
         4 Size:     200000 Seq:     646µs Par:    128µs Factor:  5.0469

# 200000
0  1.0458
1  3.1231
2  4.2737
3  4.6033
4  5.0469


Size: 200000 d -> d*1.5
0/39 1/39 2/39 3/39 4/39 5/39 6/39 7/39 8/39 9/39 10/39 11/39 12/39 13/39 14/39 15/39 16/39 17/39 18/39 19/39 20/39 21/39 22/39 23/39 24/39 25/39 26/39 27/39 28/39 29/39 30/39 31/39 32/39 33/39 34/39 35/39 36/39 37/39 38/39 39/39 -
         0 Size:     200000 Seq:     100µs Par:     34µs Factor:  2.9412
         1 Size:     200000 Seq:     176µs Par:     72µs Factor:  2.4444
         2 Size:     200000 Seq:     282µs Par:     83µs Factor:  3.3976
         3 Size:     200000 Seq:     556µs Par:    112µs Factor:  4.9643
         4 Size:     200000 Seq:     655µs Par:    124µs Factor:  5.2823
         5 Size:     200000 Seq:     856µs Par:    146µs Factor:  5.8630
         6 Size:     200000 Seq:     959µs Par:    158µs Factor:  6.0696
         7 Size:     200000 Seq:    1166µs Par:    187µs Factor:  6.2353
         8 Size:     200000 Seq:    1305µs Par:    200µs Factor:  6.5250
         9 Size:     200000 Seq:    1530µs Par:    230µs Factor:  6.6522
        10 Size:     200000 Seq:    1606µs Par:    241µs Factor:  6.6639
        11 Size:     200000 Seq:    1934µs Par:    292µs Factor:  6.6233
        12 Size:     200000 Seq:    2026µs Par:    287µs Factor:  7.0592
        13 Size:     200000 Seq:    2216µs Par:    296µs Factor:  7.4865
        14 Size:     200000 Seq:    2321µs Par:    324µs Factor:  7.1636
        15 Size:     200000 Seq:    2611µs Par:    348µs Factor:  7.5029
        16 Size:     200000 Seq:    2723µs Par:    368µs Factor:  7.3995
        17 Size:     200000 Seq:    3026µs Par:    415µs Factor:  7.2916
        18 Size:     200000 Seq:    3148µs Par:    425µs Factor:  7.4071
        19 Size:     200000 Seq:    3415µs Par:    461µs Factor:  7.4078
        20 Size:     200000 Seq:    3690µs Par:    482µs Factor:  7.6556
        21 Size:     200000 Seq:    3966µs Par:    519µs Factor:  7.6416
        22 Size:     200000 Seq:    4071µs Par:    535µs Factor:  7.6093
        23 Size:     200000 Seq:    4346µs Par:    574µs Factor:  7.5714
        24 Size:     200000 Seq:    4635µs Par:    567µs Factor:  8.1746
        25 Size:     200000 Seq:    5127µs Par:    619µs Factor:  8.2827
        26 Size:     200000 Seq:    5334µs Par:    656µs Factor:  8.1311
        27 Size:     200000 Seq:    5577µs Par:    684µs Factor:  8.1535
        28 Size:     200000 Seq:    5882µs Par:    720µs Factor:  8.1694
        29 Size:     200000 Seq:    6355µs Par:    773µs Factor:  8.2212
        30 Size:     200000 Seq:    6812µs Par:    804µs Factor:  8.4726
        31 Size:     200000 Seq:    7130µs Par:    854µs Factor:  8.3489
        32 Size:     200000 Seq:    7402µs Par:    869µs Factor:  8.5178
        33 Size:     200000 Seq:    7990µs Par:    922µs Factor:  8.6659
        34 Size:     200000 Seq:    8271µs Par:    956µs Factor:  8.6517
        35 Size:     200000 Seq:    8955µs Par:   1011µs Factor:  8.8576
        36 Size:     200000 Seq:    9131µs Par:   1031µs Factor:  8.8565
        37 Size:     200000 Seq:   10340µs Par:   1145µs Factor:  9.0306
        38 Size:     200000 Seq:   10105µs Par:   1073µs Factor:  9.4175
        39 Size:     200000 Seq:   11504µs Par:   1293µs Factor:  8.8971

# 200000
0  2.9412
1  2.4444
2  3.3976
3  4.9643
4  5.2823
5  5.8630
6  6.0696
7  6.2353
8  6.5250
9  6.6522
10  6.6639
11  6.6233
12  7.0592
13  7.4865
14  7.1636
15  7.5029
16  7.3995
17  7.2916
18  7.4071
19  7.4078
20  7.6556
21  7.6416
22  7.6093
23  7.5714
24  8.1746
25  8.2827
26  8.1311
27  8.1535
28  8.1694
29  8.2212
30  8.4726
31  8.3489
32  8.5178
33  8.6659
34  8.6517
35  8.8576
36  8.8565
37  9.0306
38  9.4175
39  8.8971


Size: 400000 Warmup
0/4 1/4 2/4 3/4 4/4 -
         0 Size:     400000 Seq:     214µs Par:     67µs Factor:  3.1940
         1 Size:     400000 Seq:     353µs Par:     94µs Factor:  3.7553
         2 Size:     400000 Seq:     565µs Par:    117µs Factor:  4.8291
         3 Size:     400000 Seq:    1169µs Par:    174µs Factor:  6.7184
         4 Size:     400000 Seq:    1317µs Par:    195µs Factor:  6.7538

# 400000
0  3.1940
1  3.7553
2  4.8291
3  6.7184
4  6.7538


Size: 400000 d -> d*1.5
0/39 1/39 2/39 3/39 4/39 5/39 6/39 7/39 8/39 9/39 10/39 11/39 12/39 13/39 14/39 15/39 16/39 17/39 18/39 19/39 20/39 21/39 22/39 23/39 24/39 25/39 26/39 27/39 28/39 29/39 30/39 31/39 32/39 33/39 34/39 35/39 36/39 37/39 38/39 39/39 -
         0 Size:     400000 Seq:     210µs Par:     70µs Factor:  3.0000
         1 Size:     400000 Seq:     352µs Par:     95µs Factor:  3.7053
         2 Size:     400000 Seq:     560µs Par:    116µs Factor:  4.8276
         3 Size:     400000 Seq:    1114µs Par:    174µs Factor:  6.4023
         4 Size:     400000 Seq:    1344µs Par:    195µs Factor:  6.8923
         5 Size:     400000 Seq:    1712µs Par:    240µs Factor:  7.1333
         6 Size:     400000 Seq:    1917µs Par:    264µs Factor:  7.2614
         7 Size:     400000 Seq:    2382µs Par:    342µs Factor:  6.9649
         8 Size:     400000 Seq:    2561µs Par:    355µs Factor:  7.2141
         9 Size:     400000 Seq:    3094µs Par:    426µs Factor:  7.2629
        10 Size:     400000 Seq:    3210µs Par:    450µs Factor:  7.1333
        11 Size:     400000 Seq:    3728µs Par:    497µs Factor:  7.5010
        12 Size:     400000 Seq:    3933µs Par:    540µs Factor:  7.2833
        13 Size:     400000 Seq:    4439µs Par:    573µs Factor:  7.7469
        14 Size:     400000 Seq:    4661µs Par:    609µs Factor:  7.6535
        15 Size:     400000 Seq:    5367µs Par:    669µs Factor:  8.0224
        16 Size:     400000 Seq:    5537µs Par:    702µs Factor:  7.8875
        17 Size:     400000 Seq:    6088µs Par:    753µs Factor:  8.0850
        18 Size:     400000 Seq:    6352µs Par:    771µs Factor:  8.2387
        19 Size:     400000 Seq:    7088µs Par:    862µs Factor:  8.2227
        20 Size:     400000 Seq:    7265µs Par:    879µs Factor:  8.2651
        21 Size:     400000 Seq:    8075µs Par:    960µs Factor:  8.4115
        22 Size:     400000 Seq:    8270µs Par:    977µs Factor:  8.4647
        23 Size:     400000 Seq:    8946µs Par:   1042µs Factor:  8.5854
        24 Size:     400000 Seq:    9187µs Par:   1096µs Factor:  8.3823
        25 Size:     400000 Seq:    9942µs Par:   1181µs Factor:  8.4183
        26 Size:     400000 Seq:   10801µs Par:   1238µs Factor:  8.7246
        27 Size:     400000 Seq:   12015µs Par:   1305µs Factor:  9.2069
        28 Size:     400000 Seq:   12179µs Par:   1388µs Factor:  8.7745
        29 Size:     400000 Seq:   12980µs Par:   1470µs Factor:  8.8299
        30 Size:     400000 Seq:   13831µs Par:   1530µs Factor:  9.0399
        31 Size:     400000 Seq:   14552µs Par:   1630µs Factor:  8.9276
        32 Size:     400000 Seq:   15076µs Par:   1668µs Factor:  9.0384
        33 Size:     400000 Seq:   16083µs Par:   1798µs Factor:  8.9449
        34 Size:     400000 Seq:   17004µs Par:   1853µs Factor:  9.1765
        35 Size:     400000 Seq:   19266µs Par:   2015µs Factor:  9.5613
        36 Size:     400000 Seq:   18757µs Par:   1975µs Factor:  9.4972
        37 Size:     400000 Seq:   21732µs Par:   2289µs Factor:  9.4941
        38 Size:     400000 Seq:   20385µs Par:   2144µs Factor:  9.5079
        39 Size:     400000 Seq:   24941µs Par:   2609µs Factor:  9.5596

# 400000
0  3.0000
1  3.7053
2  4.8276
3  6.4023
4  6.8923
5  7.1333
6  7.2614
7  6.9649
8  7.2141
9  7.2629
10  7.1333
11  7.5010
12  7.2833
13  7.7469
14  7.6535
15  8.0224
16  7.8875
17  8.0850
18  8.2387
19  8.2227
20  8.2651
21  8.4115
22  8.4647
23  8.5854
24  8.3823
25  8.4183
26  8.7246
27  9.2069
28  8.7745
29  8.8299
30  9.0399
31  8.9276
32  9.0384
33  8.9449
34  9.1765
35  9.5613
36  9.4972
37  9.4941
38  9.5079
39  9.5596
*/
