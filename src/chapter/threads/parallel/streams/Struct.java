package chapter.threads.parallel.streams;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Struct<T> implements Collector<T, Struct<T>, Struct<T>> {
    private final int SIZE = 1_000;
    private final ArrayList<ArrayList<T>> data = new ArrayList<>();
    private ArrayList<T> current;
    private int size = 0;
    private void newCurrent() {
        current = new ArrayList<>(SIZE);
        data.add(current);
    }
    public Struct() { newCurrent(); }
    public void add(T i) {
        if (current.size()==SIZE) { newCurrent(); }
        current.add(i);
        size++;
    }
    public String toSegment() {
        return size==0 ?"[]": "["+get(0)+":"+get(size-1)+"]";
    }
    public void addAll(Struct<T> s) {
        // deep copy source
        for (var e: s.data) { this.data.add(new ArrayList<>(e)); }
        // update current
        current = data.getLast();
        size += s.size;
    }
    private Struct<T> addAllShallow(Struct<T> s) {
        // shallow copy source
        data.addAll(s.data);
        // update current
        current = data.getLast();
        size += s.size;
        return this;
    }
    public int size() { return size; }
    public String toString() {
        var sb = new StringBuffer("[");
        for (var e: data) {
                for (var i: e) { sb.append(i+", "); }
        }
        sb.append("]");
        return sb.toString();
    }
    public T get(int i) {
        Objects.checkIndex(i, size);
        for (var e: data) {
            if (i<e.size()) { return e.get(i); }
            i -= e.size();
        }
        throw new IndexOutOfBoundsException(i+" "+size);
    }
    @Override
    public Supplier<Struct<T>> supplier() {
        return Struct::new;
    }
    @Override
    public BiConsumer<Struct<T>, T> accumulator() {
        return Struct::add;
    }
    @Override
    public BinaryOperator<Struct<T>> combiner() {
        return Struct::addAllShallow;
    }
    @Override
    public Set<Characteristics> characteristics() {
        return Set.of(//Characteristics.CONCURRENT,
                      //Characteristics.UNORDERED,
                      Characteristics.IDENTITY_FINISH
                      );
    }
    @Override
    public Function<Struct<T>, Struct<T>> finisher() {
        return null;
    }
}
