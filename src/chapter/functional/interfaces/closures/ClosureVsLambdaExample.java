package chapter.functional.interfaces.closures;

public class ClosureVsLambdaExample {
    public static Adder createAdder(int v) {
        return x -> x + v;
    }
    public static Adder createAdderToOne() {
        return x -> x + 1;
    }
    public static void main(String[] args) {
        Adder addOne = createAdder(1);
        Adder addOneBis = createAdder(1);
        System.out.println(addOne);
        System.out.println(addOneBis);
        
        Adder pure1 = createAdderToOne();
        Adder pure2 = createAdderToOne();
        System.out.println(pure1);
        System.out.println(pure2);
    }
}
