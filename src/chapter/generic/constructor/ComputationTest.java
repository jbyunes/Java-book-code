package chapter.generic.constructor;

import java.util.ArrayList;

public class ComputationTest {

    public static void main(String[] args) {
        var computations = new ArrayList<Computation>();
        
        {
        var p1 = new Producer<Integer>() {
            @Override public Integer produce() { return 42; }
        };
        var c1 = new Consumer<Integer>() {
            @Override public void consume(Integer e) { System.out.println(e); }
        };
        computations.add(new Computation(p1, c1));
        
        var p2 = new Producer<String>() {
            @Override public String produce() { return "Hi!"; }
        };
        var c2 = new Consumer<String>() {
            @Override public void consume(String e) { System.out.println(e.toUpperCase()); }
        };
        computations.add(new Computation(p2, c2));
        
        for (var computation: computations) {
            computation.compute();
        }
        }

        System.out.println("-----");
        
        var p1 = new Producer<Integer>() {
          @Override public Integer produce() { return 42; }
        };
        var p2 = new Producer<String>() {
          @Override public String produce() { return "Hi!"; }
        };
        
        var c = new Consumer<Object>() {
          @Override public void consume(Object e) { System.out.println(e.toString().toUpperCase()); }
        };
        
        computations.add(new Computation(p1, c));
        computations.add(new Computation(p2, c));
        
        for (var computation: computations) {
            computation.compute();
        }
        
    }

}
