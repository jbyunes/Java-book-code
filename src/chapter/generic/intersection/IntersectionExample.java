package chapter.generic.intersection;

import java.util.ArrayList;
import java.util.List;

public class IntersectionExample {
    public static <T extends Reader & Writer> List<T> create() {
        return new ArrayList<T>();
    }
    public static <T extends Reader & Writer> void print(T e) {
        System.out.println(e.getClass().getName());
        e.read();
        e.write();
    }
    public static void main(String []args) {
        var l = create();
        l.add(new RWAlt());
        l.add(new RW());
        for (var e: l) { System.out.println(e); }
        for (var e: l) { print(e); }     
    }   
    
    public static <T> void f(T v) {
        System.out.println(v.getClass().getCanonicalName());
    }
}
