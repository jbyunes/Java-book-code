package chapter.functional.utils.optionalized;

import java.util.Optional;

public class Person {
    private Optional<Home> home = Optional.empty();
    public void set(Home h) { home = Optional.ofNullable(h); }
    public Optional<Home> getHome() { return home; }
}

