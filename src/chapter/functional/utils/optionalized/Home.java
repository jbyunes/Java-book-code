package chapter.functional.utils.optionalized;

import java.util.Optional;

import chapter.functional.utils.SwimmingPool;

public class Home {
    private Optional<SwimmingPool> pool = Optional.empty();;
    public void set(SwimmingPool p) { pool = Optional.ofNullable(p); }
    public Optional<SwimmingPool> getSwimmingPool() { return pool; }
}
