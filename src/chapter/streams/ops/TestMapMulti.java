package chapter.streams.ops;

import java.util.List;
import java.util.stream.Stream;

import chapter.streams.utils.Person;
import chapter.streams.utils.TrafficLight;
import chapter.streams.utils.Tree;

public class TestMapMulti {
    public static void main(String[] args) {
        Person.employees.stream() // Stream<Person>
            .mapMulti((p, cons) -> p.salaries().forEach(cons))
            .forEach(i -> System.out.print(i+" "));
        System.out.println();

        Person.employees.stream() // Stream<Person>
            .map(Person::salaries)
            .map(List::stream)
            .mapMulti(Stream::forEach)
            .forEach(i -> System.out.print(i + " "));
        System.out.println();
        
        Person.employees.stream() // Stream<Person>
            .map(Person::salaries)
            .mapMulti(List::forEach)
            .forEach(i -> System.out.print(i + " "));
        System.out.println();
    
        var tree1 = Tree.of(5, Tree.of(2), Tree.of(7, Tree.of(6), Tree.of(9)));
        var tree2 = Tree.of(1, Tree.of(2), Tree.of(3));
        Stream.of(tree1,tree2)
            .mapMulti(Tree::expand)
            .forEach(i -> System.out.print(i+" "));
        System.out.println(); 
        
        TrafficLight.GREEN.expand(t -> System.out.print(t+" "));
        System.out.println(); 
        Stream.iterate(TrafficLight.GREEN,TrafficLight::next)
            .mapMulti(TrafficLight::expand)
            .limit(23)
            .forEach(tl -> System.out.print(tl+" "));
        System.out.println(); 
            
    }

}
