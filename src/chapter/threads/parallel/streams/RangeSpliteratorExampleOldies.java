package chapter.threads.parallel.streams;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.concurrent.RecursiveAction;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

class Recursive extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    private RangeSpliterator spliterator;

    public Recursive(RangeSpliterator spliterator) {
        this.spliterator = spliterator;
    }

    @Override
    protected void compute() {
        var right = spliterator.trySplit();
        if (right==null) {
            spliterator.forEachRemaining(e -> System.out.println(Thread.currentThread().threadId()+" "+e));
        } else {
            var rightTask = new Recursive(right);
            rightTask.fork();
            compute();
            rightTask.join();
        }
    }

}

public class RangeSpliteratorExampleOldies {
    
    public static void recurse(RangeSpliterator spliterator) {
        var split = spliterator.trySplit();
        if (split != null) {
            recurse(spliterator);
            recurse(split);
        }
        new Thread(() -> spliterator.forEachRemaining(e -> System.out.println(Thread.currentThread().threadId()+" "+e))).start();
    }

    public static void main(String[] args) {
        {
            Consumer<Spliterator<YearMonth>> job = (s) -> new Thread(() -> {
                for (int i=0; i<10; i++) {
                    s.tryAdvance(d -> System.out.println(Thread.currentThread().getName()+" "+d));
                }
                
            }).start();
            var l = new ArrayList<YearMonth>();
            for (int y = 2000; y < 2025; y++) {
                for (var m : Month.values()) {
                    l.add(YearMonth.of(y, m));
                }
            }
            var s = l.spliterator();
            var s1 = s.trySplit();
            if (s1 != null) {
                job.accept(s1);
                job.accept(s);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        {
        RangeSpliterator.of(50)
            .forEachRemaining(System.out::println);
        }
        {
            System.out.println();
            var spliterator = RangeSpliterator.of(50);
            var split = spliterator.trySplit();
            if (split != null) {
                split.forEachRemaining(System.out::println);
                spliterator.forEachRemaining(System.out::println);
            } else {
                spliterator.forEachRemaining(System.out::println);
            }
        }
//        {
//            System.out.println("recurse");
//            recurse(MySpliterator.of(t));
//        }
        {
            System.out.println("recursive action");
            var action = new Recursive(RangeSpliterator.of(50));
            action.invoke();
        }
        {
            System.out.println("stream");
            var s = StreamSupport.stream(RangeSpliterator.of(50), true);
            s.forEach(System.out::println);
        }
        {
            System.out.println("stream3");
            var s = StreamSupport.stream(RangeSpliterator.of(50), true);
            s.peek(e -> System.out.println("["+Thread.currentThread().getName()+" "+e+"]")).forEach(e -> System.out.print(e+", "));
            System.out.println();
        }
        {
            System.out.println("stream4");
            var s = StreamSupport.stream(RangeSpliterator.of(50), true);
//            System.out.println(s.spliterator().characteristics());
            var r = s.unordered().parallel().peek(System.out::println).limit(5).peek(e -> System.out.println("---"+e)).toList();
            System.out.println(r);
        }
        {
            System.out.println("stream4.1");
            var s = StreamSupport.stream(RangeSpliterator.of(50), true);
//            System.out.println(s.spliterator().characteristics());
            var r = s.parallel().peek(e -> System.out.println("---"+e)).collect(new MyCollector());
            System.out.println(r);
        }
        {
            System.out.println("stream5");
            Set<Integer> set = new TreeSet<>(
                    Arrays.asList(-9, -5, -4, -2, 1, 2, 4, 5, 7, 9, 12, 13, 16, 29, 23, 34, 57, 102, 230));
//            System.out.println(set.stream().spliterator().characteristics());
            var o = set.stream().parallel().limit(5).toList();
            System.out.println(o);
        }
    }

}

