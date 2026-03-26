package chapter.generic.joker;

import java.util.List;
import chapter.generic.method.Utils;

public class ListJokerExample {
    public static void main(String[] args) {
        System.out.println(Utils.toString(List.of(1, 2, 3, 4)));
        System.out.println(Utils.toString(List.of(1.5, 2.5, 3.5, 4.5)));
        System.out.println(Utils.toString(List.of("one","two","three")));
    }
}
