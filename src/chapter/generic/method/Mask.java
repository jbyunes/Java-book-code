package chapter.generic.method;

public class Mask<T> {
    private T value;
    public Mask(T t) { value = t; }
    public <T> void f(T t) { System.out.println(this.value+" "+t); }
}
