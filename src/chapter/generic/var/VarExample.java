package chapter.generic.var;

import java.time.LocalDate;

public class VarExample {
    public static void main(String[] args) {
        var t = new Triplet<>(3,"bonjour",LocalDate.now());
        System.out.println(t);
    }
}