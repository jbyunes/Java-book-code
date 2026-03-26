package chapter.generic.joker;

import java.util.ArrayList;
import java.util.LinkedList;
import chapter.generic.method.Utils;

public class ContravarianceExample {
    public static void main(String []args) {
        /*
        List<? super Number> l = new ArrayList<Number>();
//        l = new ArrayList<Integer>(); // forbidden
        l = new ArrayList<Object>();
        Object o = l.get(0);
        l.add(3);
        */
        var lo = new ArrayList<Object>();
        Utils.fill(lo);
        for (var o : lo) { System.out.println(o); }
        var ln = new LinkedList<Number>();
        Utils.fill(ln);
        for (var n : ln) { System.out.println(n); }
    }
}
