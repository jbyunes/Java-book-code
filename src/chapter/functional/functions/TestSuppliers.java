package chapter.functional.functions;

import java.time.LocalTime;
import java.util.function.Supplier;

public class TestSuppliers {
    public static void main(String[] args) {
        Supplier<LocalTime> ls = () -> LocalTime.now();
        var ns = Suppliers.makeFrom(ls, t -> t.getNano());
        for (int i = 0; i < 10; i++) {
            System.out.println(ns.get());
        }
    }
}
