package chapter.threads.parallel.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class IntStruct {
    private final int SIZE = 1_000;
    private final List<int []> data = new ArrayList<>();
    private int[] current;
    private int index;
    private int size = 0;
    private void newCurrent() {
        current = new int[SIZE];
        index = 0;
        data.add(current);
    }
    public IntStruct() {
        newCurrent();
        }
    public void add(Integer i) {
        if (index==SIZE) { newCurrent(); }
        current[index++] = i;
        size++;
    }
    public String toSegment() {
        return size==0 ?"[]": "["+get(0)+":"+get(size-1)+"]";
    }
    public void addAll(IntStruct s) {
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
    public IntStruct addAllShallow(IntStruct s) { // must not be used outside a stream collect process
        //        System.out.println("Recompose "+toSegment()+" with "+s.toSegment());
        data.removeLast();
        if (index!=0) { // cut current to correct size
            data.add(Arrays.copyOf(current, index));
        }
        // shallow copy source
        data.addAll(s.data);
//        for (var e: s.data) { data.add(e); }
        // update current
        current = data.getLast();
        index = s.index;
        size += s.size;
        return this;
    }
    public int dataSize() { return data.size(); }
    public int size() { return size; }
    public String toString() {
        var sb = new StringBuffer("[");
        for (var e: data) {
            if (e==current) {
                for (int i=0; i<index; i++) {
                    sb.append(current[i]+", ");
                }
            } else {
                for (var i: e) { sb.append(i+", "); }
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public int get(int i) {
        Objects.checkIndex(i, size);
        for (var e: data) {
            if (i<e.length) { return e[i]; }
            i -= e.length;
        }
        throw new IndexOutOfBoundsException(i+" "+size);
    }
}
