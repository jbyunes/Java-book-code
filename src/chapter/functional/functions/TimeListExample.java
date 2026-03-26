package chapter.functional.functions;

import java.time.LocalTime;
import static chapter.functional.functions.Utils.*;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

public class TimeListExample {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
//        var times = create(10, () -> LocalTime.now());
//        System.out.println(times);
//
//        var seconds = create(10, () -> LocalTime.now().getSecond());
//        System.out.println(seconds);
//
//        var micros = create(10, () -> LocalTime.now().getNano() / 1_000);
//        System.out.println(micros);
        
        Supplier<LocalTime> localTimes = () -> LocalTime.now();
        
//        var times = create(10, localTimes);
//        System.out.println(times);
//        
        Function<LocalTime,Integer> toSecond = t -> t.getSecond();
//        var seconds = create(10, transform(localTimes, toSecond));
//        System.out.println(seconds);
//        
        Function<LocalTime,Integer> toMicro = t -> t.getNano() / 1_000;
//        var micros = create(10, transform(localTimes, toMicro));
//        System.out.println(micros);
        
        consumeSequence(4, localTimes);
        consumeSequence(4, localTimes, toMicro, e -> System.out.println(e));
        var r = new ArrayList<Integer>();
        consumeSequence(4, localTimes, toMicro, e -> r.add(e));
        System.out.println(r);

        var res = new ArrayList<Integer>();
        consumeSequence(4, LocalTime::now, LocalTime::getSecond, res::add);
        System.out.println(res);

    }
}
