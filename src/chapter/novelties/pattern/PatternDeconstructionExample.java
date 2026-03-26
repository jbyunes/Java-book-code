package chapter.novelties.pattern;

import java.util.List;

public class PatternDeconstructionExample {
    public static void main(String[] args) {
        var o = new IntPair(666,13);
        if (o instanceof IntPair c) {
            int x = c.first();
            int y = c.second();
            System.out.println(x+" "+y);
        }
        
        if (o instanceof IntPair(int x,int y)) {
            System.out.println(x+" "+y);
        }
        
        var l = List.of(new IntPair(3,4),
                        new Pair(new IntPair(3,4),"hello"),
                        new Pair(new IntPair(3,4),List.of(1,2,3)),
                        new Pair("hello","goodbye"),
                        new Pair(List.of(1,2),new Object()));
        for (var e: l) {      
            switch (e) {
                case IntPair(int x, int y) 
                     -> System.out.println("Got: "+x+" "+y);
                case Pair(IntPair(int x, int y),Object z)
                     -> System.out.println("Got: "+x+" "+y+" "+z);
                case Pair(String s1, String s2)
                     -> System.out.println(s1+" "+s2);
                default -> System.out.println("what to do?");
            }
        }
    }
}
