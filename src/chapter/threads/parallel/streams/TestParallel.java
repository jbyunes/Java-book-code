package chapter.threads.parallel.streams;

import static chapter.threads.threads.Utils.*;


import java.util.List;

public class TestParallel {
    public static void main(String[] args) {
        var li = List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
        System.out.println("forEach");
        for (int i=0; i<2; i++) {
            li.stream()
                .parallel()
                .sorted()
                .peek(e -> System.out.print(e+"["+tid()+"] "))
                .forEach(e->System.out.print(e+"("+tid()+") "));
            System.out.println();
        }
        System.out.println();
        System.out.println("forEachOrdered");
        for (int i=0; i<2; i++) {
            li.stream()
                .parallel()
                .sorted()
                .peek(e -> System.out.print(e+"["+tid()+"] "))
                .forEachOrdered(e -> System.out.print(e+"("+tid()+") "));
            System.out.println();
        }
        System.out.println();
    }
}
