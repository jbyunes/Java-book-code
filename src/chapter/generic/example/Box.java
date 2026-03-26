package chapter.generic.example;

public record Box<T>(T value) {}

//public class Box<T> {
//    private T value;
//    public Box(T v) { value = v; }
//    public T getValue() { return value; }
//    public void setValue(T v) { value = v; }
//}