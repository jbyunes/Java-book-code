package chapter.generic.joker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import chapter.generic.method.Utils;

public class PECSExample {

    public static void main(String[] args) {
        var li = List.of(1,2,3);
        var ln = new ArrayList<Number>();
        Utils.copy(ln,li); 
        for (var n: ln) { System.out.println(n); }
        var ls = List.of("Hello","Bye");
        var lo = new LinkedList<Object>();
        Utils.copy(lo,ls);
        for (var o : lo) { System.out.println(o); }
    }
}
