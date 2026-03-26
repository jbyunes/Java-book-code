package chapter.functional.monads;

public class BasicMonadExample {
    public static void main(String[] args) {
        var test = BasicMonad.of(666)
                .flatMap(v -> BasicMonad.of("Value="+v));
        System.out.println(test.get());
    }
}
