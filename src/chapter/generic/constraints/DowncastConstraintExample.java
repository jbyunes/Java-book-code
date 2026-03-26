package chapter.generic.constraints;


public class DowncastConstraintExample {

    public static void main(String[] args) {
        Number n = Integer.valueOf(999);
        Integer i = Utils.functionalDownCast(n);
        System.out.println(i);
        //		String s = Utils.functionalDownCast(n); // forbidden


        Pair<Object,Integer> p = new Pair<>();
        //		Pair<Integer,Object> p2; // forbidden
    }

}
