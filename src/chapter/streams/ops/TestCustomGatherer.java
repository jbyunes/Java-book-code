package chapter.streams.ops;

import static java.util.stream.Gatherer.*;

import java.util.Random;


public class TestCustomGatherer {
    public static void main(String[] args) {
        var r = new Random().ints(1,4)
//              .parallel()
            .limit(30)
            .peek(e -> System.out.print(e+", "))
            .mapToObj(e -> e)
            .gather(ofSequential(DistinctAdjacent<Integer>::new,
                                 DistinctAdjacent<Integer>::gather))
            .toList();
          System.out.println();
          System.out.println("size="+r.size()+" "+r);
          System.out.println();

        for (int i=0; i<100; i++) {
            var l = new Random().ints(1,4)
                .limit(30).mapToObj(e -> e).toList();
            System.out.println("size="+l.size()+" "+l);
            var encoded = l.stream()
                .gather(ofSequential(RLEEncoder::new,
                                     RLEEncoder::gather,
                                     RLEEncoder::finish))
                .toList();
            System.out.println("encoded size="+encoded.size()+" "+encoded);
            var decoded = encoded.stream()
                .gather(ofSequential(RLEDecoder::new, RLEDecoder::gather))
                .toList();
            System.out.println("decoded size="+decoded.size()+" "+decoded);
            assert l.equals(decoded);
            System.out.println();
        }
    }
}
