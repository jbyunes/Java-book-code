package chapter.functional.functions;

import java.util.function.Supplier;

public class ExceptionEndingEvenSupplier implements Supplier<Integer> {
    private int valeur = 0;
    @Override
    public Integer get() {
        if (valeur>10) throw new RuntimeException("Supplier exhausted");
        int r = valeur;
        valeur += 2;
        return r;
    }
}
