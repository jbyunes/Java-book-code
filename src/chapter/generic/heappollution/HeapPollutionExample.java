package chapter.generic.heappollution;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class HeapPollutionExample {
    
    public static void simple() {
        List<String> l = new ArrayList<>();
        List lraw = l;
        List<Object> lo = (List<Object>)lraw;
        lo.add(new Object());
        System.out.println("coucou");
        System.out.println(l);
        System.out.println(lo.get(0));      
        System.out.println(l.get(0)); // génère une exception!
    }
    
    public static void simple2() {
        @SuppressWarnings("rawtypes")
        List l = new ArrayList<Number>();
        @SuppressWarnings("unchecked")
        List<String> ls = l; // Unchecked warning
        ls.forEach(null);
    }
    public static void simple3() {
        bad("hello","goodbye");
    }
    public static void simple4() {
        List<String> l = new ArrayList<String>();
        l.add("hello");
        l.add("goodbye");
        bad(l);
        System.out.println(l);
        Consumer<String> c = new Consumer<>() {
            @Override
            public void accept(String e) {
                System.out.println(e.getClass().getCanonicalName());
            }            
        };
        for (var e: l) {
            c.accept(e);
        }
    }

    public static void main(String[] args) {
//        simple();
        
//        simple2();
        
//        simple3();
        
        simple4();

        
//        bad(List.of("hello","goodbye"),List.of("foo","bar"));
//        List<String> l = Collections.checkedList(new ArrayList<String>(), String.class);        
    }
    
    public static void bad(String... strings) {
        Object[] array = strings;
        array[0] = 42;
        for (var e: array) {
            System.out.println(e);
        }
        for (var e: strings) {
            System.out.println(e);
        }
    }
    
    /*
    @SafeVarargs
    public static void bad(List<String>... stringLists) {
        Object[] array = stringLists;
        array[0] = 42;
        for (var e: array) {
            System.out.println(e);
        }
    }
    */
    
    public static void bad(List l) {
        l.add(42);
    }
}
