package chapter.functional.monads;

public class OptionalMonadExample {
    public static void main(String[] args) {
        var r = OptionalMonad.of("21")
                .flatMap(Utils::tryParse)
                .map(x -> x*2)
                .getOrElse(666);
        System.out.println(r);
        var r2 = OptionalMonad.of("oups")
                .flatMap(Utils::tryParse)
                .map(x -> x*2)
                .getOrElse(666);
        System.out.println(r2);        
    }
}
