package chapter.threads.parallel.streams;

import static chapter.threads.parallel.streams.Utils.*;

import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import static chapter.threads.threads.Utils.*;

public class TestCollect {
    public static void main(String[] args) {
        final int MAX = 20_000_000;

        System.out.println("IntStruct");
        printInfos();
        for (int size=10_000, step=10_000; size<MAX; size+=step, step *= 2) {
            final int s = size;

            Callable<IntStruct> job1s = () -> IntStream.range(0,s)
                .limit(s)
                .collect(IntStruct::new, IntStruct::add, IntStruct::addAll);
            Callable<IntStruct> job1p = () -> IntStream.range(0,s)
                .limit(s)
                .parallel()
                .collect(IntStruct::new, IntStruct::add, IntStruct::addAll);
            Callable<IntStruct> job1ps = () -> IntStream.range(0,s)
                .limit(s)
                .parallel()
                .collect(IntStruct::new, IntStruct::add, IntStruct::addAllShallow);
            long seq = measure(job1s);
            long par = measure(job1p);
            long pars = measure(job1ps);
            double f1 = (double)seq/par;
            double f2 = (double)seq/pars;
            System.out.printf(
                "Size: %9d Seq: %8d Par: %8d Sha: %8d S/P: %7.4f S/S: %7.4f\n",
                size, seq,par,pars,f1,f2);
        }
        System.out.println("-");
    }
}
/*
IntStruct
Size:     10000 Seq:       54 Par:       72 Sha:       44 S/P:  0,7500 S/S:  1,2273
Size:     20000 Seq:       84 Par:       55 Sha:       45 S/P:  1,5273 S/S:  1,8667
Size:     40000 Seq:      174 Par:       65 Sha:       51 S/P:  2,6769 S/S:  3,4118
Size:     80000 Seq:      328 Par:       87 Sha:       62 S/P:  3,7701 S/S:  5,2903
Size:    160000 Seq:      792 Par:      121 Sha:       85 S/P:  6,5455 S/S:  9,3176
Size:    320000 Seq:     1032 Par:      181 Sha:      129 S/P:  5,7017 S/S:  8,0000
Size:    640000 Seq:     1610 Par:      290 Sha:      179 S/P:  5,5517 S/S:  8,9944
Size:   1280000 Seq:     3208 Par:      516 Sha:      303 S/P:  6,2171 S/S: 10,5875
Size:   2560000 Seq:     6518 Par:      924 Sha:      560 S/P:  7,0541 S/S: 11,6393
Size:   5120000 Seq:    12937 Par:     1940 Sha:     1085 S/P:  6,6686 S/S: 11,9235
Size:  10240000 Seq:    25407 Par:     3797 Sha:     2121 S/P:  6,6913 S/S: 11,9788
*/
