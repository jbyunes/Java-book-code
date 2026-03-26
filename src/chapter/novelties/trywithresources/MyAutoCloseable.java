package chapter.novelties.trywithresources;

public class MyAutoCloseable implements AutoCloseable {
    private String n;
    public MyAutoCloseable(String n) { this.n = n; }
    @Override public void close() throws Exception {
        throw new Exception("close() "+n);
    }   
    @Override public String toString() { return n; }
}