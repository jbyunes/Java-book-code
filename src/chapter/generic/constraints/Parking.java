package chapter.generic.constraints;

import java.util.ArrayList;
import java.util.List;

import vehicles.Vehicle;

public final class Parking<T extends Vehicle> {
    private List<T> places = new ArrayList<>();
    public void park(T v) { places.add(v); }
}