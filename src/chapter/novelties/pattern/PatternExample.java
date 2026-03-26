package chapter.novelties.pattern;

public class PatternExample {
    public static void main(String[] args) {
        Object o = new Pair(13,"foo");
        switch(o) {
            case Pair p -> System.out.println(p.first()+" "+p.second());
            default     -> System.out.println("what to do?");
        }
    }

}
