package chapter.functional.interfaces.closures;

import chapter.functional.interfaces.Computable;

public class ClosureExample {

    public static void main(String[] args) {
        Computable add1 = x -> x + 1;
        System.out.println(add1.compute(1));
        Computable add2 = x -> x + 2;
        System.out.println(add2.compute(1));
        
        int value = 10;
        {
            Computable addValue = x -> x + value;
            System.out.println(addValue.compute(1));
        }
        
        {
            Computable addValue = new AdderClosure(value);
            System.out.println(addValue.compute(1));
        }    }

}
