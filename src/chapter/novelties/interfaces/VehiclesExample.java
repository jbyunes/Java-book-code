package chapter.novelties.interfaces;

import vehicles.Vehicle;
import vehicles.VehicleFactory;
import vehicles.Vehicles;

public class VehiclesExample {
    public static void main(String[] args) throws Exception {
        /*
        Vehicle v = VehicleFactory.create("fr");
        System.out.println(v);
        System.out.println(Vehicles.madeIn(v));
        */
        
        Vehicle v = Vehicle.create("fr");
        System.out.println(v);
        System.out.println(Vehicle.madeIn(v));
    }
}
