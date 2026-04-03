package vehicles;

public interface Vehicle {
    public static Vehicle create(String countryCode) throws Exception {
        return switch(countryCode) {
            case "fr" -> new Panhard();
            case "us" -> new Oldsmobile();
            default   -> throw new Exception("Bad country code");
        };
    }
    /* Old fashioned */
//  public static Vehicle create(String countryCode) throws Exception {
//      switch(countryCode) {
//          case "fr": return new Panhard();
//          case "us": return new Oldsmobile();
//          default:   throw new Exception("Bad country code");
//      }
//  }   

    @SuppressWarnings("unused")
	public static String madeIn(Vehicle v) {
        /* Old fashioned */
//        if (v instanceof Panhard) { return "fr"; }
//        if (v instanceof Oldsmobile) { return "us"; }
//        return "Unknown";
        
        return switch(v) {
            case Panhard p    -> "fr";
            case Oldsmobile o -> "us";
            default           -> "unknown";
        };
    }

}
