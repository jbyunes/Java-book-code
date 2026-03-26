package chapter.functional.interfaces.closures;

public class ImpurityExample {
    public static void main(String[] args) {
        IntGenerator gen = new IntGenerator();
        Adder add = x -> x+gen.get();
        System.out.println(add.to(1));
        System.out.println(add.to(1));
    }
}
