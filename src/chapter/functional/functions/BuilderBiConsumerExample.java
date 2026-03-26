package chapter.functional.functions;

import java.util.Map;
import java.util.function.Consumer;

public class BuilderBiConsumerExample {
    public static void main(String[] args) {
        var map = Map.of(1, "one", 2, "two", 3, "three");
        Consumer<Integer> kc = k -> System.out.println("Key: "+k);
        Consumer<String> vc = v -> System.out.println("Value: "+v);
        var c = Builders.build(kc, vc);
        map.forEach(c);
    }

}
