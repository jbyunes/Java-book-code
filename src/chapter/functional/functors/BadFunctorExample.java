package chapter.functional.functors;

public class BadFunctorExample {
    public static void main(String[] args) {
        var bf = BasicFunctor.of(42);
        var r = bf.map(BasicFunctor::of);
        System.out.println(r.get()); // BasicFunctor!!!
    }

}
