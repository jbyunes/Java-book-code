package chapter.functional.monads;

public class ReviveExample {
    public static void main(String[] args) {
        var r = OptionalRevivableMonad.of("21")
                .flatMap(Utils::tryParseRevivable)
                .map(x -> x * 2)
                .revive(666)
                .map(x -> x + 1)
                .getOrElse(0);
        System.out.println(r);      
        var r2 = OptionalRevivableMonad.of("oups")
                .flatMap(Utils::tryParseRevivable)
                .map(x -> x * 2)
                .revive(666)
                .map(x -> x + 1)
                .getOrElse(0);
        System.out.println(r2);
    }
}

