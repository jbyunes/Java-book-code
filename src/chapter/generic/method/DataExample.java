package chapter.generic.method;

public class DataExample {
    public static void main(String[] args) {
        var d = new Data<>(5);      // Data<Integer>
        var m = d.makeMap("five");  // Map<String,Integer>
        System.out.println(m);
        var m2 = d.makeMap(555);    // Map<Integer,Integer>
        System.out.println(m2);        
    }
}
