package vehicles;

public final class Vehicles {
    private Vehicles() {}
    public static String madeIn(Vehicle v) {
        if (v instanceof Panhard) { return "fr"; }
        if (v instanceof Oldsmobile) { return "us"; }
        return "Unknown";
        /*
        return switch(v) {
            case Panhard p    -> "fr";
            case Oldsmobile o -> "us";
            default           -> "unknown";
        };
        */
    }
}
