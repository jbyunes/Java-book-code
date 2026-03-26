package chapter.novelties.interfaces;

public class AmphibiousVehicle implements Drivable, Floatable {
    @Override public String getType() {
        return Floatable.super.getType() +
            " & " + Drivable.super.getType();
    }
}
