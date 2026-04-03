package chapter.generic.constraints;

import vehicles.Vehicle;

public class BadInstanciationExample {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
		Parking<Vehicle> pv = null;
//        Parking<LocalDate> p = null;
    }
}
