package chapter.generic.joker;

import java.util.ArrayList;
import java.util.List;
import chapter.generic.method.Utils;

public class RotateExample {
    public static void main(String[] args) {
        List<Integer> li = new ArrayList<>();
        li.add(1); li.add(2); li.add(3);      
        System.out.println(Utils.toString(li));
        Utils.rotate(li);
        System.out.println(Utils.toString(li));
        
        List<String> ls = new ArrayList<>();
        ls.add("one"); ls.add("two"); ls.add("three");
        System.out.println(Utils.toString(ls));
        Utils.rotate(ls);
        System.out.println(Utils.toString(ls));
    }
}
