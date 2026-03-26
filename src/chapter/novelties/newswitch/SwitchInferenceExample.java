package chapter.novelties.newswitch;

public class SwitchInferenceExample {
    public static void main(String[] args) {
        for (int v=1; v<5; v++) {
            var i = switch(v) {
                case 1 -> Integer.MAX_VALUE;
                case 2 -> Double.valueOf(2.5);
                case 3 -> 4.5f;
                default -> (short)0;
            };
            System.out.println(i);
        }

        for (int v=1; v<3; v++) {
            var i = switch(v) {
                case 1 -> 1;
                default -> true;
            };
            System.out.println(i);
        }
        
        Integer i = null;
        if (i==null) {
            System.out.println("null");
        } else {
            switch(i) {
                case 1:
                    System.out.println("1");
                    break;
                case 2:
                    System.out.println("2");
                    break;
                default:   
                    System.out.println("big");
            }
        }
        
        if (i==null) {
            System.out.println("null");
        } else {
            switch (i) {
                case 1  -> System.out.println("1");
                case 2  -> System.out.println("2");
                default -> System.out.println("big");
            }
        }
        switch (i) {
            case null -> System.out.println("null");
            case 1    -> System.out.println("1");
            case 2    -> System.out.println("2");
            default   -> System.out.println("big");
        }
        switch (i) {
            case null, default -> System.out.println("oups");
            case 1    -> System.out.println("1");
            case 2    -> System.out.println("2");
        }


    }
}
