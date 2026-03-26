package chapter.generic.constraints;

public class CrossExample {
    public static void main(String[] args) {
        Cross.test(new MyInteger(100),new MyDouble(45.5));
    }
}
