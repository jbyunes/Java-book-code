package chapter.novelties.pattern;

import shapes.*;

public class PatternMatchingExample {
    public static void main(String[] args) {
        Shape f = null;
        String s = switch(f) {
            case Oval o      -> "Oval";
            case Rectangle o -> "Rectangle";
            case Triangle o  -> "Triangle";
            case null        -> "null";
        };
        System.out.println(s);
    }
}
