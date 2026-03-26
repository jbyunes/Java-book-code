package chapter.functional.functions;

import java.util.HashMap;
import java.util.List;

public class MergeExample {
    public static void main(String[] args) {
        var count = new HashMap<Integer,Integer>();
        List.of(1, 4, 1, 5, 2, 3, 4, 1)
            .forEach(e -> count.merge(e, 1, (c1,c2)->c1+c2));
        count.forEach((k,v) -> System.out.println(k + " is present " + v + " times"));
    }
}
