package chapter.functional.optional;

import java.util.Optional;

import chapter.functional.utils.Home;
import chapter.functional.utils.Person;
import chapter.functional.utils.SwimmingPool;

public class OptionalMapExample {
    public static void main(String[] args) {
        Person joe = new Person();
        Home joesGarage = new Home();
        joe.set(joesGarage);

        Optional<Integer> length = getLengthOfHisSwimmingPool(joe);
        System.out.println(length.map(l -> "Available pool length is "+l)
            .orElse("No available pool"));
    }
    public static Optional<Integer> getLengthOfHisSwimmingPool(Person p) {
        return Optional.of(p)
                .map(Person::getHome)
                .map(Home::getSwimmingPool)
                .map(SwimmingPool::length);
    }
}