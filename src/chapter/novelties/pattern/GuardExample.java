package chapter.novelties.pattern;

import java.util.Scanner;

public class GuardExample {
    public static void main(String[] args) {
        String obj = "foobar";
        switch(obj) {
            case String s when s.length()>5  -> System.out.println(">5");
            case String s when s.length()>50 -> System.out.println(">50");
            default                          -> System.out.println("default");
        }
        
        try (var scan = new Scanner(System.in)) {
            System.out.print("What? ");
            switch (scan.nextLine()) {
                case "y", "Y"
                     ->  System.out.println("You win");
                case String s when s.length()<10
                     -> System.out.println("Short string");
                case String s when s.length()>=10
                     -> System.out.println("Long string");
                default
                     -> System.out.println("Weird");
            }
        }
    }
}
