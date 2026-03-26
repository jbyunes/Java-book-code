package chapter.novelties.interfaces;

public class FastCar implements FastDrivable {
    @Override
    public String getType() {
        return "Fast" + FastDrivable.super.getType();
    }

}
