package chapter.functional.functions;

import java.util.function.Supplier;

public class NullEndingEvenSupplier implements Supplier<Integer> {
    private int valeur = 0;
    @Override
    public Integer get() {
        if (valeur>10) return null;
        int r = valeur;
        valeur += 2;
        return r;
    }
}

