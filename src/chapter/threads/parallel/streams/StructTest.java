package chapter.threads.parallel.streams;

import static chapter.threads.parallel.streams.Utils.measure;
import static chapter.threads.threads.Utils.*;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class StructTest {
    public static void main(String [] args) {
        final int MAX = 40_000_000;

        System.out.println("Struct");
        printInfos();
        for (int size=10_000,step=10_000; size<MAX; size+=step,step *= 2) {
            final int s = size;
            
            var l = Stream.iterate(0,i->i+1)
                    .limit(s).toList();

            Callable<ArrayList<Integer>> job1l = () ->
//                Stream.iterate(0,i->i+1) .limit(s)
                l.stream().collect(ArrayList::new,
                    ArrayList::add,ArrayList::addAll);
            Callable<Struct<Integer>> job1s = () ->
//                Stream.iterate(0,i->i+1) .limit(s)
                    l.stream().collect(Struct::new,
                        Struct::add,Struct::addAll);
            Callable<Struct<Integer>> job1p = () ->
//                Stream.iterate(0,i->i+1).limit(s)
                l.stream().parallel().collect(Struct::new,
                    Struct::add,Struct::addAll);
            Callable<Struct<Integer>> job1c = () ->
//                Stream.iterate(0,i->i+1).limit(s)
                l.stream().parallel().collect(new Struct<>());
            long ll = measure(job1l);
            long seq = measure(job1s);
            long par = measure(job1p);
            long parc = measure(job1c);
//            double f1 = (double)seq/par;
//            double f2 = (double)seq/parc;
//            double f3 = (double)ll/parc;
            System.out.printf("Size: %9d\tList: %9d\tSeq: %9d\tPar: %9d\tOpt: %9d\n", size,ll,seq,par,parc);
//            System.out.printf("Size: %9d List: %9d Seq: %8d Par: %8d ",
//                              size, ll, seq, par);
//            System.out.printf("Col: %9d S/P: %7.4f S/C: %7.4f L/C: %7.4f\n",
//                              parc, f1, f2, f3);
        }
        System.out.println("-");

    }

}
/*
Struct
Mac OS X, 26.2, aarch64
Oracle Corporation, 25
Available processing units: 14
Thread pool size: 13
Size:     10000 List:        89 Seq:        85  Par:       238  Opt:       133
Size:     20000 List:       168 Seq:        68  Par:        94  Opt:        53
Size:     40000 List:       136 Seq:       123  Par:        76  Opt:        54
Size:     80000 List:       289 Seq:       264  Par:       104  Opt:        79
Size:    160000 List:       521 Seq:       532  Par:       144  Opt:       133
Size:    320000 List:      1082 Seq:      1074  Par:       221  Opt:       224
Size:    640000 List:      5730 Seq:      2110  Par:       664  Opt:       324
Size:   1280000 List:      7932 Seq:      4519  Par:       719  Opt:       583
Size:   2560000 List:     12269 Seq:      8687  Par:      1247  Opt:      1091
Size:   5120000 List:     28215 Seq:     17261  Par:      2567  Opt:      2117
Size:  10240000 List:     63355 Seq:     34664  Par:      6246  Opt:      4629
Size:  20480000 List:    132020 Seq:     69202  Par:     11268  Opt:      8246
*/