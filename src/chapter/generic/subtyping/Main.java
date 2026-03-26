package chapter.generic.subtyping;

public class Main {
    public static void main(String[] args) {
        var po = new UniformPair<>(123, "foo");
        System.out.println(po);
        
        UniformPair<Object> po1 = new UniformPair<>(666, "foo");
        System.out.println(po1);
        
        Pair<Object,Object> po2 = new UniformPair<>(123, "foo");
        System.out.println(po2);

        var ps = new PairOfStrings("hello","bye");
        System.out.println(ps);
    }
}
