package chapter.novelties.pattern;

public class BasicExamples {

    public static void main(String[] args) {
        Object o = "Hello, World!";
        if (! (o instanceof String s)) {
            System.out.println("Not a string");
            return;
        } else {
            System.out.println(s.toUpperCase());
        }
        System.out.println(s.toUpperCase());
    }

}
