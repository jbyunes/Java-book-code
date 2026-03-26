package chapter.functional.functions;

import java.util.function.IntSupplier;

public class EvenIntSupplier implements IntSupplier {
    private int valeur = 0;
    @Override
    public int getAsInt() {
        int r = valeur;
        valeur += 2;
        return r;
    }
}

