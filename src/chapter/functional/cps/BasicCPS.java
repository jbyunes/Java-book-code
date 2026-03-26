package chapter.functional.cps;

import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

public class BasicCPS {
    private static Scanner s = new Scanner(System.in);
    
    public static int sum(int n) {
        int sum = 0;
        for (int i=0; i<n; i++) { sum += i; }
        return sum;
    }
    
    public static int product(int n) {
        int product = 1;
        for (int i=1; i<n; i++) { product *= i; }
        return product;
    }
    public static int readAnInt() {
        System.out.print("Value ? ");
        return s.nextInt();
    }

    public static int readAnInt(IntUnaryOperator continuation) {
        return continuation.applyAsInt(readAnInt());
    }
    public static void readAnInt(IntConsumer continuation) {
        continuation.accept(readAnInt());
    }
    public static IntConsumer andThen(IntUnaryOperator o, IntConsumer s) {
        return i -> s.accept(o.applyAsInt(i));
    }
    public static IntConsumer andThen(IntConsumer o, Runnable r) {
        return i -> { o.accept(i); r.run(); };
    }
    public static void main(String[] args) {
        int v, r;
        v = readAnInt();
        r = sum(v);
        System.out.println(r);
        v = readAnInt();
        r = product(v);
        System.out.println(r);
        
        v = readAnInt(BasicCPS::sum);
        System.out.println(v);
        v = readAnInt(BasicCPS::product);
        System.out.println(v);
        
        readAnInt(andThen(BasicCPS::sum, (IntConsumer)System.out::println) );
        readAnInt(andThen(BasicCPS::product, (IntConsumer)System.out::println) );
        
        System.out.println("here");
        readAnInt(andThen(BasicCPS::sum, andThen(System.out::println, () -> readAnInt(andThen(BasicCPS::product, (IntConsumer)System.out::println)))));
        
    }

}
