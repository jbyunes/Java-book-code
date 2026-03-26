package chapter.functional.recursion;

import java.util.function.IntUnaryOperator;

public class RecursionExample {
    int i = this.i+3;
    // recursive instance function 
    IntUnaryOperator factorial =
        x -> x == 1 ? x : x * this.factorial.applyAsInt(x - 1);
    // recursive class function
    static IntUnaryOperator sFactorial =
        x -> x == 1 ? x : x * RecursionExample.sFactorial.applyAsInt(x - 1);
    public static void main(String[] args) {
        System.out.println(new RecursionExample().factorial.applyAsInt(10));
        System.out.println(sFactorial.applyAsInt(10));
    }
}
