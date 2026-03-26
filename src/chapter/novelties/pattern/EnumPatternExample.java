package chapter.novelties.pattern;

import species.*;

public class EnumPatternExample {
    public static void main(String[] args) {
        Species a = null;
        switch(a) {
            case Mammals.COW  -> System.out.println("Moo");
            case Mammals.CAT  -> System.out.println("Meow");
            case Mammals.LION -> System.out.println("Roar");
            case Reptiles t   -> System.out.println("____");
        }
    }
}
