package chapter.generic.intersection;

public class PairIntDouble {
    private int i;
    private double d;
    public <T extends ConvertibleToInt & ConvertibleToDouble>
        PairIntDouble(T c) {
        this.i = c.intValue();
        this.d = c.doubleValue();
    }
    public String toString() { return "int="+i+" double="+d; }
}
