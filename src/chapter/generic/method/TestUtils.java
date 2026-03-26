package chapter.generic.method;

import java.time.LocalDate;

public class TestUtils {
    public static void main(String[] args) {
        Integer []ti = { 33, 44, 66, 23, 99 };
        System.out.println(Utils.toString(ti));

        Double []tf = { 4., 3.14, 1.414 };
        System.out.println(Utils.toString(tf));
        
        LocalDate[] td = { LocalDate.now(), LocalDate.of(2024,12,25) };
        System.out.println(Utils.toString(td));
    }
}
