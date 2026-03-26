package chapter.streams.reductions;

import java.util.function.BiFunction;

import chapter.streams.utils.Person;

public class AllMatchByReductionExample {
    public static void main(String[] args) {
        BiFunction<Boolean,Person,Boolean> andSalary1000 =
            (a,e) -> a && e.salary()>=1000;
        BiFunction<Boolean,Person,Boolean> andSalary2500 =
            (a,e) -> a && e.salary()>=2500;
        boolean allPaidCorrectly = Person.employees.stream()
            .reduce(true, andSalary1000, Boolean::logicalAnd);
        System.out.println("All have been paid correctly is "
            +allPaidCorrectly+".");
        boolean allVeryWellPaid = Person.employees.stream()
            .reduce(true, andSalary2500, Boolean::logicalAnd);
        System.out.println("All have been very well paid is "
            +allVeryWellPaid+".");        
    }
}
