package chapter.generic.method;

public final class PrettyPrinters {
    private PrettyPrinters() {}
    public static <T> void prettyPrint(Iterable<T> t) {
        System.out.print("[");
        for (var e: t) { System.out.print(" "+e); }
        System.out.println(" ]");
    }
    public static <T> void prettyPrint(T t) {
        System.out.println("{ "+t+" }");
    }
    public static void prettyPrint(Number n) {
        System.out.println("Here is a number "+n);
    }
    public static void prettyPrint(int n) {
        System.out.println("That one is an int "+n);
    }
    public static <T, U> void prettyPrint(Iterable<T> t, U u) {
        System.out.println("It<T>,U");
    }
    public static <T, U> void prettyPrint(T t, Iterable<U> u) {
        System.out.println("T, It<U>");
    }
    /*
    public static <T, U> void prettyPrint(Iterable<T> t, Iterable<U> u) {
        System.out.println("It<T>, It<U>");
    }
    */
}
