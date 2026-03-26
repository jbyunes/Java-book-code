package chapter.functional.interfaces.closures;

public class ThisAndLambdaExample {
    @FunctionalInterface
    interface I {
        public void f();
        default public void printThis() {
            System.out.println(this);
        }
    }

    static class C {
        public I createI() {
            return () -> System.out.println(this); 
        }
    }
    public static void main(String[] args) {
        var c = new C();
        System.out.println(c);
        var i = c.createI();
        System.out.println(i);
        i.f();
        i.printThis();
    }

}
