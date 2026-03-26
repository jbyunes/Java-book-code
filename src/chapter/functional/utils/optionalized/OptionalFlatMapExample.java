package chapter.functional.utils.optionalized;

import java.util.Optional;

import chapter.functional.utils.SwimmingPool;

public class OptionalFlatMapExample {
    public static Optional<Integer> getLengthOfHisSwimmingPool(Person p) {
        return Optional.of(p)
                .flatMap(Person::getHome)
                .flatMap(Home::getSwimmingPool)
                .map(SwimmingPool::length);
    }
    public static void main(String[] args) {
        Person joe = new Person();
        Home joesGarage = new Home();
        joe.set(joesGarage);
        
        Optional<Integer> l = getLengthOfHisSwimmingPool(joe);
        System.out.println(l.map(i -> "Available pool of length "+i+"M")
            .orElse("No pool!"));
    }
}
