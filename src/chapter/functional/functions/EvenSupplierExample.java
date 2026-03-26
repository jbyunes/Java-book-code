package chapter.functional.functions;

public class EvenSupplierExample {
    public static void main(String[] args) {
        EvenSupplier s = new EvenSupplier();       
        for (int i=0; i<10; i++) {
            System.out.print(s.get()+" ");
        }
        System.out.println();
    }
}
