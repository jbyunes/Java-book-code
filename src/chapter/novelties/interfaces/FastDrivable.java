package chapter.novelties.interfaces;

public interface FastDrivable extends Drivable  {
    private static String concat(String s1, String s2) {
        return s1 + " " + s2;
    }
    @Override
    public default String getType() {
        return concat("Fast ", Drivable.super.getType());
    }
}
