package chapter.generic.method;

import java.time.LocalDate;
import java.util.List;

public class PrettyPrintersExample {
    public static void main(String[] args) {
        PrettyPrinters.prettyPrint(0);
        PrettyPrinters.prettyPrint(0.6);
        PrettyPrinters.prettyPrint(LocalDate.now());
        PrettyPrinters.prettyPrint(List.of(5,6));
        
        PrettyPrinters.prettyPrint(List.of(5,6),4);
        PrettyPrinters.prettyPrint(4, List.of(5,6));
        // Ambiguous
//        PrettyPrints.prettyPrint(List.of(5,6),List.of(4,3)); // Which one ?

        PrettyPrinters.<Object,Iterable<Integer>>prettyPrint(List.of(5,6),List.of(4,3));
    }
}
