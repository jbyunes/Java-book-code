package chapter.functional.functions;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerExample {
    public static <T> void printSequence(Supplier<T> s, int max) {
        consumeSequence(s,max,e->System.out.println(e));
    }
    public static <T> void consumeSequence(Supplier<T> s,
                                           int max,
                                           Consumer<T> c) {
        while(max-->0) { c.accept(s.get()); }
    }

    public static void main(String[] args) {
        consumeSequence(new EvenSupplier(), 10, i -> System.out.print(i+" "));
        System.out.println();
        
        var evenSequence = new EvenSupplier();
        Consumer<Integer> toScreen = i -> System.out.println("Screen:"+i);
        Consumer<Integer> toNetwork = i -> System.out.println("Net:"+i);
        consumeSequence(evenSequence, 10, toScreen.andThen(toNetwork));

    }
}
