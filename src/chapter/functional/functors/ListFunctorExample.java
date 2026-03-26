package chapter.functional.functors;

import java.time.LocalDate;

public class ListFunctorExample {
    public static void main(String []a) {
        var r = ListFunctor.of(1, 2, 3, 4, 5)
                .map(x -> x/2.3)
                .map(Double::longValue)
                .map(LocalDate::ofEpochDay);
        System.out.println("Result="+r);
    }
}
