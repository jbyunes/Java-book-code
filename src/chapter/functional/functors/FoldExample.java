package chapter.functional.functors;

public class FoldExample {
    public static void main(String[] args) {
        var list = ListFunctor.of(1,3,5,2,4);
        var sum = list
                .map(x -> x*10)
                .foldLeft(0, Integer::sum);
        System.out.println(sum);
        var prod = list
                .map(x -> x*10)
                .foldLeft(1, (x,y)->x*y);
        System.out.println(prod);
        var min = list.foldLeft(Integer.MAX_VALUE, Integer::min);
        var max = list.foldLeft(Integer.MIN_VALUE, Integer::max);
        System.out.println("Min="+min+" Max="+max);
    }
}
