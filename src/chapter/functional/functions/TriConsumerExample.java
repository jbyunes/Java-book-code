package chapter.functional.functions;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TriConsumerExample {
    private static <T,U,V> void compute(T t, U u, V v,
            TriConsumer<? super T,? super U,? super V> c) {
        c.accept(t,u,v);
    }
    public static void main(String[] args) {
        TriConsumer<String,LocalDate,String> c =
            (t,u,v) -> System.out.println(t+" "+u+" "+v);
        TriConsumer<String,LocalDate,Object> c2 =
            (t,u,v) -> System.out.println(t+"---"+u+"---"+v);
        compute("a",LocalDate.now(),"c",c.andThen(c2));
        
        BiConsumer<Integer,Consumer<Integer>> squarer =
            (x,f) -> f.accept(x*x);
        TriConsumer<Integer,Integer,Consumer<Integer>> adder =
            (x,y,f) -> f.accept(x+y);
        TriConsumer<Integer,Integer,Consumer<Integer>> g =
                (a,b,f) -> squarer.accept(a,
                    x -> squarer.accept(b, y -> adder.accept(x,y,f)));
        g.accept(3,4,System.out::println);

    }
}
