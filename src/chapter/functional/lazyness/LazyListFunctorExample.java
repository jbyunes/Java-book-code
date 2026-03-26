package chapter.functional.lazyness;

import static chapter.functional.lazyness.Iterators.*;

public class LazyListFunctorExample {
    public static void main(String[] args) {
        System.out.println("Iterating over LazyListFunctor:");
        var l = LazyListFunctor.of(create(0, 5));
        System.out.println(l);
        for( var e: l ) {
            System.out.println(e);
            System.out.println(l);
        }
        
        System.out.println("\nAccessing elements by index: 3 then 2");
        var l1 = LazyListFunctor.of(create(0, 5));
        System.out.println("l1="+l1);
        System.out.println(l1.get(3).map(x -> "Value: "+x)
                                    .orElse("Out of bounds"));
        System.out.println("l1="+l1);
        System.out.println(l1.get(2).map(x -> "Value: "+x)
                                    .orElse("Out of bounds"));
        System.out.println("l1="+l1);

        System.out.println("\nAccessing elements by index: 3");
        var l2 = LazyListFunctor.of(create(100, 110));
        System.out.println("l2="+l2);
        System.out.println(l2.get(3).map(x -> "Value: "+x)
                                    .orElse("Out of bounds"));
        System.out.println("l2="+l2);
        
        System.out.println("\nConcatenating then accessing index: 11");
        var l3 = LazyListFunctor.concat(l1, l2);
        System.out.println("l3="+l3);
        System.out.println(l3.get(11).map(x -> "Value: "+x)
                           .orElse("Out of bounds"));
        System.out.println("l1="+l1);
        System.out.println("l2="+l2);
        System.out.println("l3="+l3);
        
        System.out.println("\nMapping over concatenated list:");
        var l4 = l3.map(x -> x*2);
        for (var e: l4) {
            System.out.println(e);
            System.out.println("l1="+l1);
            System.out.println("l2="+l2);
            System.out.println("l3="+l3);
            System.out.println("l4="+l4);
        }
        
        System.out.println("\nConcatenating infinite list with finite one:");
        var l5 = LazyListFunctor.of(create(1000));
        var l6 = LazyListFunctor.concat(l5,l1);
        System.out.println(l6);
        var it = l6.iterator();
        for (int i=0; i<7; i++) {
            if (it.hasNext()) {
                System.out.println(it.next());
                System.out.println("l6="+l6);
            }
        }
    }
}
