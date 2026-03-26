package chapter.novelties.newswitch;

import java.util.Scanner;

//class A {}
//class B extends A {}
//class C extends B {}
//class D extends B {}


public class TestSwitch {

    public static void test1() {

        try (var scanner = new Scanner(System.in)) {
            System.out.print("Give me a car brand: ");
            String brand = scanner.nextLine().toLowerCase();
            String country = "unknown";
            switch (brand) {
                case "peugeot":
                case "renault":
                case "hotchkiss":
                    country = "french";
                    break;
                case "ford":
                case "buick":
                    country = "american";
            }
            System.out.println(brand+" is a "+country+" car brand.");
        }

        try (var scanner = new Scanner(System.in)) {
            System.out.print("Give me a car brand: ");
            String brand = scanner.nextLine().toLowerCase();
            String country = "unknown";
            switch(brand.toLowerCase()) {
                case "peugeot", "renault", "hotchkiss"
                    -> country = "french";
                case "ford", "buick"
                    -> country = "american";
            }
            System.out.println(brand+" is a "+country+" car brand.");
        }

        try (var scanner = new Scanner(System.in)) {
            System.out.print("Give me a car brand: ");
            String brand = scanner.nextLine().toLowerCase();
            String country = switch(brand.toLowerCase()) {
                case "peugeot", "renault", "hotchkiss" -> "french";
                case "ford", "buick"                   -> "american";
                default                                -> "unknown";
            };
            System.out.println(brand+" is a "+country+" car brand.");
        }

        try (var scanner = new Scanner(System.in)) {
            System.out.print("Give me a car brand: ");
            String brand = scanner.nextLine().toLowerCase();
            String country = switch(brand.toLowerCase()) {
                case "peugeot", "renault", "hotchkiss" -> "french";
                case "ford", "buick"                   -> "american";
                default -> {
                        System.out.println("oups");
                        yield "unknown";
                    }
            };
            System.out.println(brand+" is a "+country+" car brand.");
        }
        
    }

    public static void main(String[] args) {
        test1();

//        for (int v=1; v<3; v++) {
//            var i = switch(v) {
//                case 1 -> new C();
//                default -> new D();
//            };
//            System.out.println(i);
//        }
//
//        Integer v = 3;
//        switch(v) {
//            case Integer i -> System.out.println("Integer"); 
//
//            //            case Number n  -> System.out.println("Number");
//        }
//
//        Object ob = v;
//        switch(ob) {
//            case Integer i -> System.out.println("Integer");
//            case Number n  -> System.out.println("Number");
//            case Object o  -> System.out.println("Object");
//        }

    }

}


