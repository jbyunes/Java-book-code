package chapter.functional.functions;

import java.util.function.Function;
import java.util.function.IntSupplier;

class SuperClass {
    public int m(int x) {
        return x;
    }
    public IntSupplier fromSuperClass = super::hashCode;
}

interface Interface {
    default int m(int x) {
        return x;
        }
}

class A extends SuperClass implements Interface {
    public Function<Integer, Integer> fromSuperClass = super::m;
    public Function<Integer, Integer> fromInterface = Interface.super::m;
}

public class ArrayNewReferenceExample {
    public static <T> T create(int n, Function<Integer, T> factory) {
        return factory.apply(n);
    }

    public static void main(String[] args) {        
        var a1d = create(12, Integer[]::new);
        System.out.println(a1d.length);

        var a3d = create(12, Integer[][][]::new);
        System.out.println(a3d.length);
        
        var b = create(12, Integer::valueOf);
        System.out.println(b);
        
    }
}
