package chapter.generic.constraints;

import java.io.Serializable;

public class Data<T extends Serializable & Comparable<T>> {
    private T v;
    public Data(T v) { this.v = v; }
    public void saveToFile(String filename) { }
    public int compareWith(T v) { return this.v.compareTo(v); }
}