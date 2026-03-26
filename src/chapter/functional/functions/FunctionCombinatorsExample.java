package chapter.functional.functions;

import static chapter.functional.functions.Utils.*;

import java.time.LocalTime;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class FunctionCombinatorsExample {
    public static void main(String[] args) {
        Supplier<LocalTime> times = () -> LocalTime.now();
        
        Function<LocalTime,Integer> toNano = t -> t.getNano();
        Function<Integer,Integer> nanoToMicro = n -> n / 1_000;
        Function<Integer,String> toString = i -> i+"µs";
        consumeSequence(5,
                        times,
                        toNano.andThen(nanoToMicro).andThen(toString),
                        e -> System.out.println(e));
        consumeSequence(5,times, toString.compose(nanoToMicro).compose(toNano), e -> System.out.println(e));
        
        ToIntFunction<LocalTime> toNanoInt = t -> t.getNano();
        IntUnaryOperator nanoToMicroInt = n -> n / 1_000;
        IntFunction<String> toMsString = i -> i+"µs";
        
        consumeSequence(5,
                        times,
                        combine(toNanoInt,nanoToMicroInt,toMsString),
                        System.out::println);


//        consumeSequence(10,
//                        times,
//                        ((Function<LocalTime,Integer>)
//                            t -> t.getNano()).andThen(toString),
//                        e -> System.out.println(e));
//        consumeSequence(10,
//                        times,
//                        toString.compose(t -> t.getNano()),
//                        e -> System.out.println(e));
//        
//        consumeSequence(10,
//                        times,
//                        toNano.andThen(i -> "("+i+")"),
//                        e -> System.out.println(e));
//        consumeSequence(10,
//                        times,
//                        ((Function<Integer,String>)
//                            i -> "("+i+")").compose(toNano),
//                        e -> System.out.println(e));
    }
}
