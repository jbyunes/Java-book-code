package chapter.functional.functions;


public class ReturnTypeExample {
    @FunctionalInterface
    interface I {
        void m(int x);
    }
    public static void f(I i) {
        i.m(3);
    }  
    public static int g(int i) {
        System.out.println(i);
        return i;
    }
    public static void main(String[] args) {
        f(System.out::println);
        f(ReturnTypeExample::g);
//        f(i -> { System.out.println(i); return i; }); // Wrong
    }
}
