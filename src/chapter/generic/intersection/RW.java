package chapter.generic.intersection;

public class RW implements Reader, Writer {
    public void read() { System.out.println("read"); }
    public void write() { System.out.println("write"); }
}