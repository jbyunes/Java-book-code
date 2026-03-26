package chapter.threads.parallel.streams;

import static java.lang.Math.cos;

import java.util.stream.IntStream;

public class IntStructTest {
    public static void main(String []args) {
        var s = new IntStruct();
        System.out.println(s);

        for (int i=0; i<4; i++) s.add(i);
        System.out.println(s);

        for (int i=0; i<4; i++) s.add(i);
        System.out.println(s);

        var s2 = new IntStruct();
        for (int i=0; i<7; i++) s2.add(i);
        System.out.println(s2);

        s.addAll(s2);
        System.out.println(s+" "+s2);    

        for (int i=0; i<s.size(); i++) {
            System.out.print(s.get(i)+" ");
        }
        System.out.println();
        
        int SIZE=100_000_000;
        {
            long beg = System.nanoTime();
            IntStruct r = null;
            for (int n=0; n<10; n++) {
                r = IntStream.range(0,SIZE).limit(SIZE)
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(IntStruct::new, IntStruct::add, IntStruct::addAll);
            }
            long end = System.nanoTime();
            double f = (double)r.size()/r.dataSize();
            System.out.println(r.dataSize()+" "+f);
            System.out.println(((end-beg)/1000/10)+"µs");
            System.out.println(r.get(SIZE/2));
        }
        {
            long beg = System.nanoTime();
            IntStruct r = null;
            for (int n=0; n<10; n++) {
                r = IntStream.range(0,SIZE).limit(SIZE).parallel()
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(IntStruct::new, IntStruct::add, IntStruct::addAll);
            }
            long end = System.nanoTime();
            double f = (double)r.size()/r.dataSize();
            System.out.println(r.dataSize()+" "+f);
            System.out.println(((end-beg)/1000/10)+"µs");
            System.out.println(r.get(SIZE/2));
        }
        {
            long beg = System.nanoTime();
            IntStruct r = null;
            for (int n=0; n<10; n++) {
                r = IntStream.range(0,SIZE).limit(SIZE).parallel()
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(IntStruct::new, IntStruct::add, IntStruct::addAllShallow);
            }
            long end = System.nanoTime();
            double f = (double)r.size()/r.dataSize();
            System.out.println(r.dataSize()+" "+f);
            System.out.println(((end-beg)/1000/10)+"µs");
            System.out.println(r.get(SIZE/2));
        }
    }
}
