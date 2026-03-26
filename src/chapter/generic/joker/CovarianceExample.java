package chapter.generic.joker;

import java.util.List;
import java.util.Set;

import chapter.generic.method.Utils;

public class CovarianceExample {
    public static void main(String[] args) {
        /*
        List<? extends Number> l = new ArrayList<Number>();
        l = new ArrayList<Integer>();
        l = new ArrayList<Object>(); // forbidden
        Number ee = l.get(0);
        */

        var li = List.of(1, 2, 3);
        var ln = Utils.convert(li);
        for (var n : ln) { System.out.println(n); }
        var lf = Set.of(1.5, 2.5, 3.5);
        ln = Utils.convert(lf);
        for (var n : ln) { System.out.println(n); }        
    }

}
