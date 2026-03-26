package chapter.novelties.records;

public class RecordExample {
    public static void main(String[] args) {
        Point p = new Point(5,6);
        System.out.println(p);
        System.out.println("Ordinate is "+p.y());
        
        System.out.println();
        var t = new IntTuple(1, 2, 3, 4, 5);
        System.out.println(t.values());
        for (var e: t) {
            System.out.println(e);
        }
        System.out.println();
        t = IntTuple.of(1, 2, 3, 4, 5);
        for (var e: t) {
            System.out.println(e);
        }
    }    
}
