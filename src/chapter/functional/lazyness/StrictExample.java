package chapter.functional.lazyness;

public class StrictExample {
    public static void f(boolean b, int x, int y) {
        System.out.println(b?x:y);
    }   
    public static int problem() {
        boolean b = true;
        while (b) {}
        return 1;
    }
    public static void main(String[] args) {
        f(false, problem(),4);
    }
}
