package vehicles;

public final class VehicleFactory {
    private VehicleFactory() {}

    public static Vehicle create(String countryCode) throws Exception {
        return switch(countryCode) {
            case "fr" -> new Panhard();
            case "us" -> new Oldsmobile();
            default -> throw new Exception("Bad country code");
        };
    }	
    /* Old fashioned */
//    public static Vehicle create(String countryCode) throws Exception {
//        switch(countryCode) {
//            case "fr": return new Panhard();
//            case "us": return new Oldsmobile();
//            default:   throw new Exception("Bad country code");
//        }
//    }   
}
