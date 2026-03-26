package chapter.functional.monads;

public class Utils {
    private Utils() {}
    public static OptionalMonad<Integer> tryParse(String s) {
        try {
            final int i = Integer.parseInt(s);
            return OptionalMonad.of(i);
        } catch (NumberFormatException e) {
            return OptionalMonad.empty();
        }
    }
    public static OptionalRevivableMonad<Integer> tryParseRevivable(String s) {
        try {
            final int i = Integer.parseInt(s);
            return OptionalRevivableMonad.of(i);
        } catch (NumberFormatException e) {
            return OptionalRevivableMonad.empty();
        }
    }
}
