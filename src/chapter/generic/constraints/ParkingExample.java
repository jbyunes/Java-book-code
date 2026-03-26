package chapter.generic.constraints;

import vehicles.*;

public class ParkingExample {
    public static void main(String[] args) {
        var pv = new Parking<Vehicle>();
        pv.park(new Car());
        pv.park(new Bicycle());
        
        var pb = new Parking<Bicycle>();
        pb.park(new Bicycle());        
    }
}
