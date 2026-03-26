package chapter.functional.functions;

import java.util.function.IntSupplier;

public class EvenIntSupplierExample {
    public static void printSequence(IntSupplier s, int max) {
        for (int i=0; i<max; i++) { System.out.print(s.getAsInt()+" "); }
        System.out.println();
    }
    public static void main(String[] args) {
        printSequence(new EvenIntSupplier(),10);
    }
}

