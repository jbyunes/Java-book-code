package chapter.functional.functions;

import java.util.function.Supplier;

public class EvenSupplier implements Supplier<Integer> {
    private int valeur = 0;
    @Override
    public Integer get() {
        int r = valeur;
        valeur += 2;
        return r;
    }
}

