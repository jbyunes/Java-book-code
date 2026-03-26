package chapter.threads.parallel.streams;

import static java.lang.Math.cos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class MyStruct<T> {
    private IntFunction<T []> allocator;
    private final int SIZE = 1_000;
    private final List<T []> data = new ArrayList<>();
    private T[] current;
    private int index;
    private int size = 0;
    private void newCurrent() {
        current = allocator.apply(SIZE);
        index = 0;
        data.add(current);
    }
    public MyStruct(IntFunction<T []> allocator) {
        this.allocator = allocator;
        newCurrent();
    }
    public void add(T e) {
        if (index==SIZE) { newCurrent(); }
        current[index++] = e;
        size++;
    }
    public String toSegment() {
        return size==0 ?"[]": "["+get(0)+":"+get(size-1)+"]";
    }
    public void addAll(MyStruct<? extends T> s) {
        //        System.out.println("Recompose "+toSegment()+" with "+s.toSegment());
        data.removeLast();
        if (index!=0) { // cut current to correct size
            data.add(Arrays.copyOf(current, index));
        }
        // deep copy source
        for (var e: s.data) { this.data.add(e.clone()); }
        // update current
        current = data.getLast();
        index = s.index;
        size += s.size;
    }
    public void addAllShallow(MyStruct<? extends T> s) { // must not be used outside a stream collect process
        //        System.out.println("Recompose "+toSegment()+" with "+s.toSegment());
        data.removeLast();
        if (index!=0) { // cut current to correct size
            data.add(Arrays.copyOf(current, index));
        }
        // shallow copy source
        for (var e: s.data) { data.add(e); }
        // update current
        current = data.getLast();
        index = s.index;
        size += s.size;
    }
    public int size() {
        return size;
    }
    public String toString() {
        var sb = new StringBuffer("[");
        for (var e: data) {
            if (e==current) {
                for (int i=0; i<index; i++) {
                    sb.append(current[i]+", ");
                }
            } else {
                for (var i: e) {
                    sb.append(i+", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public T get(int i) {
        Objects.checkIndex(i, size);
        for (var e: data) {
            if (i<e.length) { return e[i]; }
            i -= e.length;
        }
        throw new IndexOutOfBoundsException(i+" "+size);
    }
    public static void main(String []args) {
        var s = new MyStruct<>(Integer[]::new);
        System.out.println(s);

        for (int i=0; i<4; i++) s.add(i);
        System.out.println(s);

        for (int i=0; i<4; i++) s.add(i);
        System.out.println(s);

        var s2 = new MyStruct<>(Integer[]::new);
        for (int i=0; i<7; i++) s2.add(i);
        System.out.println(s2);

        s.addAll(s2);
        System.out.println(s+" "+s2);    

        for (int i=0; i<s.size(); i++) {
            System.out.print(s.get(i)+" ");
        }
        System.out.println();
        
        int SIZE=10_000_000;
        {
            long beg = System.nanoTime();
            var r = Stream.iterate(0, i -> i+1)
                    .limit(SIZE)
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(() -> new MyStruct<>(Integer[]::new), MyStruct::add, MyStruct::addAll);
            long end = System.nanoTime();
            double f = (double)r.size()/r.data.size();
            System.out.println(r.data.size()+" "+f);
            System.out.println(((end-beg)/1000)+"µs");
            System.out.println(r.get(SIZE/2));
        }
        {
            long beg = System.nanoTime();
            var r = Stream.iterate(0, i -> i+1)
                    .limit(SIZE)
                    .parallel()
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(() -> new MyStruct<>(Integer[]::new), MyStruct::add, MyStruct::addAll);
            long end = System.nanoTime();
            double f = (double)r.size()/r.data.size();
            System.out.println(r.data.size()+" "+f);
            System.out.println(((end-beg)/1000)+"µs");
            System.out.println(r.get(SIZE/2));
        }
        {
            long beg = System.nanoTime();
            var r = Stream.iterate(0, i -> i+1)
                    .limit(SIZE)
                    .parallel()
                    .map(i -> (int)(cos((double)i/100)*100))
                    .collect(() -> new MyStruct<>(Integer[]::new), MyStruct::add, MyStruct::addAllShallow);
            long end = System.nanoTime();
            double f = (double)r.size()/r.data.size();
            System.out.println(r.data.size()+" "+f);
            System.out.println(((end-beg)/1000)+"µs");
            System.out.println(r.get(SIZE/2));
        }
    }

}
