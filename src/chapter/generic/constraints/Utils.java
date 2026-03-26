package chapter.generic.constraints;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    <K, L extends K> List<K> f(List<L> l) {
        List<K> lk = new ArrayList<>();
        for (L e : l) { lk.add(e); }
        return lk;
    }
    
    @SuppressWarnings("unchecked")
    public static <T, S extends T> S functionalDownCast(T v) { return (S)v; }

}
