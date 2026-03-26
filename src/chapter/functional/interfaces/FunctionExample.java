package chapter.functional.interfaces;

import chapter.functional.functions.Utils;

public class FunctionExample {
    public static void main(String[] args) {
        Utils.evaluate(new Squarer(), 3);
        
        Utils.evaluate(new Computable() {
            @Override public int compute(int x) { return x * x; }
        }, 3);

        Utils.evaluate(x -> x*x, 3);        
    }
}
