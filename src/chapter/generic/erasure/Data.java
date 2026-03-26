package chapter.generic.erasure;

import java.io.Serializable;

public class Data<T extends Serializable & Comparable<T>> {
    private T data;
    public Data(T v) { data = v; }
    public T getData() { return data; }
    public void setData(T d) { data = d; }
    public static void main(String []args) {
        Data<Integer> d = new Data<>(55);
        Integer i = d.getData();
        System.out.println(i);
    }
}