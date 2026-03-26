package chapter.functional.functions;

import java.util.function.IntSupplier;

public class NegativeEndingEvenIntSupplier implements IntSupplier {
    private int valeur = 0;
    @Override public int getAsInt() {
        if (valeur>10) return -1;
        int r = valeur;
        valeur += 2;
        return r;
    }

}
