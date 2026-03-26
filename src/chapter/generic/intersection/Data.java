package chapter.generic.intersection;

public class Data implements ConvertibleToInt, ConvertibleToDouble {
    @Override public double doubleValue() { return 666.; }
    @Override public int intValue() { return 666; }    
}