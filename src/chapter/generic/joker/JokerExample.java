package chapter.generic.joker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JokerExample {
    private static <T> void test(List<T> l, T t) {
        l.add(t);
    }
    private static <T> void test(List<T> l) {
        l.add(l.get(0));
    }
    public static void main(String[] args) {
        List<?> l = new ArrayList<Integer>();
        l = new ArrayList<Number>();
        l = new ArrayList<Object>();
        l = new ArrayList<List<Map<Integer,LocalDate>>>();
        
        Object o = l.get(0); // syntactically correct
//        l.add(new Object());
//        test(l,new Object());
        test(l); // syntactically correct
        l.add(null);
    }
}
