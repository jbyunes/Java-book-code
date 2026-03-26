package chapter.streams.ops;

import chapter.streams.utils.Person;

public class TestAllMatch {
    public static void main(String[] args) {
        boolean allPaidCorrectly = Person.employees.stream()
            .allMatch(e -> e.salary()>=1000);
        System.out.println("All have been paid correctly is "+allPaidCorrectly+".");
        boolean allVeryWellPaid = Person.employees.stream()
                .peek(System.out::println)
                .allMatch(e -> e.salary()>2500);
        System.out.println("All have been very well paid is "+allVeryWellPaid+".");        
    }
}
