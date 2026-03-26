package chapter.functional.functors;

import java.time.LocalDate;

public class BasicFunctorExample {
    public static void main(String []args) {
        var result = BasicFunctor.of(5)
            .map(x -> x/2.3)
            .map(Double::longValue)
            .map(LocalDate::ofEpochDay);
        System.out.println(result);
    }
}
