package chapter.novelties.pattern;

import java.util.ArrayList;
import java.util.List;

public class NullInPatternExample {
    public static List<Object> getSomething() {
        var l = new ArrayList<Object>();
        l.add(new Pair(3,4));
        l.add(null);  // on ajoute un null dans la liste...
        l.add(new Pair(new IntPair(3,4),"hello"));
        return l;
    }
    public static void main(String[] args) {
        for (var anObject : getSomething()) {
            switch (anObject) {
                case Pair(Integer x, Integer y)
                  -> System.out.println("Got: "+x+" "+y);
                case Pair(IntPair(int x, int y),Object z)
                  -> System.out.println("Got: "+x+" "+y+" "+z);
                case null -> System.out.println("oh oh");
                default   -> System.out.println("what to do?");
            }
        }
    }
}
