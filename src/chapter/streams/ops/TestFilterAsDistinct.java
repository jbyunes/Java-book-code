package chapter.streams.ops;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.IntPredicate;

public class TestFilterAsDistinct {

    public static void main(String[] args) {
        var distinctFilter = new IntPredicate() {
            private Set<Integer> seen = new HashSet<>();
            @Override
            public boolean test(int value) {
                synchronized(seen) {
                    return seen.add(value);
                }
            }          
        };
        new Random(2).ints(0, 20)
            .parallel()
            .limit(300)
            .filter(distinctFilter)
            .sorted()
            .forEachOrdered(i -> System.out.print(i+" "));
        System.out.println();
   }

}
