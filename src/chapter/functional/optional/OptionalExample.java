package chapter.functional.optional;

import java.util.Objects;

import chapter.functional.utils.Home;
import chapter.functional.utils.Person;
import chapter.functional.utils.SwimmingPool;

public class OptionalExample {
    public static void main(String[] args) {
        Person joe = new Person();
        Home joesGarage = new Home();
        joe.set(joesGarage);

        int l = getLength(joe);
        if (l==0) {
            System.out.println("No pool!");
        } else {
            System.out.println("Available pool of length "+l+"M");

        }
    }
    public static int getLength(Person p) {
        if (p!=null) {
            Home h = p.getHome();
            if (h!=null) {
                SwimmingPool sp = h.getSwimmingPool();
                if (sp!=null) {
                    return sp.length(); 
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    public static int getLengthWithNonNull(Person p) {
        try {
            Objects.requireNonNull(p);
            Objects.requireNonNull(p.getHome());
            Objects.requireNonNull(p.getHome().getSwimmingPool());
            return p.getHome().getSwimmingPool().length();
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
