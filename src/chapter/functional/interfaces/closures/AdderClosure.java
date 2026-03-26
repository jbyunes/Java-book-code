package chapter.functional.interfaces.closures;

import chapter.functional.interfaces.Computable;

public class AdderClosure implements Computable {
    private final int capturedValue; 
    public AdderClosure(int capturedValue) {
        this.capturedValue = capturedValue;
    }
    @Override
    public int compute(int v) {
        return capturedValue + v;
    }
}
