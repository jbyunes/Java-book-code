package chapter.generic.example;

public class TestGenericExample {

    public static void main(String[] args) {
        MyList<Integer> l = new MyLinkedList<>();
        for (int i = 0; i<10; i++) {
            l.add(i);
        }
        for (int i=0; i<l.size(); i++) {
            System.out.print(l.get(i)+", ");
        }
        System.out.println();
        for (int i: l) {
            System.out.print(i+",");
        }
        System.out.println();
        System.out.println(l.get(100));
    }

}
